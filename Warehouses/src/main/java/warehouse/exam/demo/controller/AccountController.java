package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.model.Accounts;
import warehouse.exam.demo.service.AccountService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AccountController {
    private final String url = "http://localhost:9999/api/auth";
    private final AuthenticationManager authenticationManager;
    @Autowired
    AccountService accountService;

    @Autowired
    public AccountController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("account", new Accounts());
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        try {
//            ResponseEntity<Map> response = restTemplate
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            String username = authentication.getName();
            session.setAttribute("username", username);
            session.setAttribute("loggedInUser", true);
            return "redirect:/auth/index";
        } catch (AuthenticationException ex) {
            model.addAttribute("errorMessage", "Invalid Email Or Password!");
            return "login/login";
        }
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password, Model model) {
//        AccountDAO accountDAO = AccountDAO.builder()
//                .email(email)
//                .password(password)
//                .build();
//        try {
//            ResponseEntity<Map> response = restTemplate.postForEntity(
//                    "http://localhost:9999/api/login",
//                    accountDAO,
//                    Map.class);
//            Map<String, Object> responseBody = response.getBody();
//            boolean isError = (boolean) responseBody.get("error");
//            String message = (String) responseBody.get("message");
//            if (!isError) {
//                // Xử lý thành công - Thêm thông tin vào model và chuyển hướng
//                httpSession.setAttribute("isAuthenticated", true);
//                return "redirect:/itemdata/index";
//            } else {
//                // Xử lý thất bại - Thêm thông tin lỗi vào model và hiển thị lại form đăng nhập
//                model.addAttribute("errorMessage", message);
//                return "login/login";
//            }
//        } catch (HttpClientErrorException ex) {
//            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
//                // Xử lý lỗi đăng nhập không hợp lệ (401 Unauthorized)
//                model.addAttribute("errorMessage", "Invalid Email Or Password!");
//                return "login/login";
//            } else if (ex.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
//                // Xử lý lỗi máy chủ (500 Internal Server Error)
//                model.addAttribute("errorMessage", "Server Error!");
//                return "login/login";
//            }
//        }
//        return null;
//    }

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
        model.addAttribute("account", new Accounts());
        return "account/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AccountDAO dao) {
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

//    @GetMapping("/index")
//    public String index(Model model, HttpSession session) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cookie", "JSESSIONID=" + session.getId());
//        ParameterizedTypeReference<List<Accounts>> responseType = new ParameterizedTypeReference<>() {
//        };
//        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<List<Accounts>> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
//
//        model.addAttribute("account", response.getBody());
//        return "account/index";
//    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
