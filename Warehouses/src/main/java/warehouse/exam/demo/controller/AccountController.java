package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.model.Accounts;
import warehouse.exam.demo.model.AccountsRoles;
import warehouse.exam.demo.model.Roles;
import warehouse.exam.demo.reponsitory.RolesRepository;
import warehouse.exam.demo.service.AccountService;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AccountController {
    private final RestTemplate restTemplate;
    private final AuthenticationManager authenticationManager;
    @Autowired
    AccountService accountService;
    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    public AccountController(RestTemplate restTemplate, AuthenticationManager authenticationManager) {
        this.restTemplate = restTemplate;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("account", new Accounts());
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        AccountDAO accountDAO = AccountDAO.builder()
                .email(email)
                .password(password)
                .build();
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "http://localhost:9999/api/login", accountDAO, Map.class);
            Map responseBody = response.getBody();
            assert responseBody != null;
            boolean isError = (boolean) responseBody.get("error");
            String message = (String) responseBody.get("message");
            String username = (String) responseBody.get("username");
            if (!isError && response.getStatusCode() == HttpStatus.OK) {
                session.setAttribute("username", username);
                session.setAttribute("loggedInUser", true);
                session.setAttribute("message", message);
                return "redirect:/auth/index";
            } else {
                model.addAttribute("errorMessage", message);
                return "login/login";
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                model.addAttribute("errorMessage", "Invalid Email Or Password!");
                return "login/login";
            } else {
                model.addAttribute("errorMessage", "Server Error!");
            }
            return "login/login";
        }
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<String> roleAccess = List.of("admin", "supper_admin");
        List<String> roleOfUser = List.of("admin", "employee");
        boolean isAccess = roleOfUser.stream().filter(roleAccess::contains) != null;
        if(!isAccess){
//            is no access
            return "account/index";
        }
        List<AccountDAO> searchList = (List<AccountDAO>) model.asMap().get("searchResults");
        if (searchList != null) {
            model.addAttribute("account", searchList);
        } else {
            model.addAttribute("account", accountService.findAll());
        }
        return "account/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, RedirectAttributes redirectAttributes) {
        List<AccountDAO> foundOrders = accountService.searchAllAccount(keyword);
        redirectAttributes.addFlashAttribute("searchResults", foundOrders);
        return "redirect:/auth/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Roles> rolesList = rolesRepository.findAll();
        model.addAttribute("rList", rolesList);
        model.addAttribute("account", new Accounts());
        return "account/create";
    }

//    @PostMapping("/create")
//    public String create(@ModelAttribute AccountDAO dao, Model model) {
//        List<Roles> rolesList = rolesRepository.findAll();
//        model.addAttribute("rList", rolesList);
//        accountService.saveAccount(dao);
//        return "redirect:/auth/index";
//    }

    @PostMapping("/create")
    public String create(@ModelAttribute AccountDAO dao, Model model) {

        // Lấy mã vai trò từ form và cập nhật vào AccountDAO
        Collection<AccountsRoles> accountCodes = dao.getRoleId();
        dao.setRoleId(accountCodes);

        accountService.saveAccount(dao);
        return "redirect:/auth/index";
    }


    @GetMapping("/edit/{code}")
    public String update(Model model, @PathVariable("code") String code) {
        Accounts acc = accountService.findOne(code);
        model.addAttribute("accounts", acc);
        return "account/edit";
    }

    @PostMapping("/edit")
    public String update(AccountDAO accountDAO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/account/edit";
        }
        accountService.updateAccount(accountDAO);
        return "redirect:/auth/index";
    }

    @PostMapping(value ="/updatePassword/{code}")
    public String updatePassword(@PathVariable("code") String code, @RequestParam("newPassword") String newPassword) {
        accountService.updateAccountPassword(code, newPassword);
        return "redirect:/auth/index";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
