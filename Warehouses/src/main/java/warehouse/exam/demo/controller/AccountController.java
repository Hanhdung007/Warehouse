package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.model.*;
import warehouse.exam.demo.reponsitory.AccountRolesRepository;
import warehouse.exam.demo.reponsitory.RolesRepository;
import warehouse.exam.demo.service.AccountService;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
public class AccountController{
    private final RestTemplate restTemplate;
    private final AuthenticationManager authenticationManager;
    @Autowired
    AccountService accountService;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    AccountRolesRepository accountRolesRepository;

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
            String getName = (String) responseBody.get("getName");
                if (!isError && response.getStatusCode() == HttpStatus.OK) {
                    CustomUserDetails userDetails = accountService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    session.setAttribute("username", username);
                    session.setAttribute("loggedInUser", true);
                    session.setAttribute("message", message);
               
                    session.setAttribute("getName", getName);
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
@PreAuthorize("hasAnyRole('admin', 'whManager')")
    public String index(Model model, Authentication auth, HttpSession sesson) {
        List<AccountDAO> searchList = (List<AccountDAO>) model.asMap().get("searchResults");
        if (searchList != null) {
            model.addAttribute("account", searchList);
        } else {
            model.addAttribute("account", accountService.findAll());
        }
        return "account/index";
    }

    @PreAuthorize("hasAnyRole('admin', 'qc', 'sale', 'whManager')")
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, RedirectAttributes redirectAttributes) {
        List<AccountDAO> foundOrders = accountService.searchAllAccount(keyword);
        redirectAttributes.addFlashAttribute("searchResults", foundOrders);
        return "redirect:/auth/index";
    }

    @PreAuthorize("hasRole('qc')")
    @GetMapping("/create")
    public String create(Model model) {
        List<Roles> rolesList = rolesRepository.findAll();
        model.addAttribute("rList", rolesList);
        model.addAttribute("account", new Accounts());
        return "account/create";
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/create")
    public String create(@ModelAttribute AccountDAO dao, @RequestParam("roleIds") Collection<Integer> roleIds) {
        accountService.saveAccount(dao);
        List<AccountsRoles> accountsRoles = roleIds.stream().map(roleId -> {
            AccountRolesId accountRolesId = new AccountRolesId();
            accountRolesId.setAccountCode(dao.getCode());
            accountRolesId.setRoleId(roleId);

            AccountsRoles accountsRole = new AccountsRoles();
            accountsRole.setId(accountRolesId);
            accountsRole.setAccountCode(dao.getCode());
            accountsRole.setRoleId(roleId);
            return accountsRole;
        }).toList();
        accountRolesRepository.saveAll(accountsRoles);
        return "redirect:/auth/index";
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/edit/{code}")
    public String update(Model model, @PathVariable("code") String code) {
        Accounts acc = accountService.findOne(code);
        List<Roles> rolesList = rolesRepository.findAll();
        List<Integer> selectedRoleIds = acc.getAccountsRolesById()
                .stream()
                .map(accountsRoles -> accountsRoles.getId().getRoleId())
                .collect(Collectors.toList());
        model.addAttribute("rList", rolesList);
        model.addAttribute("accounts", acc);
        model.addAttribute("selectedRoleIds", selectedRoleIds);
        return "account/edit";
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/edit")
    public String update(@ModelAttribute AccountDAO accountDAO, BindingResult bindingResult, @RequestParam("roleIds") Collection<Integer> roleIds) {
        if (bindingResult.hasErrors()) {
            return "/account/edit";
        }

        List<AccountsRoles> existingRoles = accountRolesRepository.findAllByAccountCode(accountDAO.getCode());
        for (AccountsRoles accountsRoles : existingRoles) {
            if (!roleIds.contains(accountsRoles.getRoleId())) {
                accountRolesRepository.delete(accountsRoles);
            }
        }
        for (Integer roleId : roleIds) {
            if (existingRoles.stream().noneMatch(role -> role.getRoleId().equals(roleId))) {
                AccountRolesId accountRolesId = new AccountRolesId();
                accountRolesId.setAccountCode(accountDAO.getCode());
                accountRolesId.setRoleId(roleId);

                AccountsRoles newRole = new AccountsRoles();
                newRole.setId(accountRolesId);
                newRole.setAccountCode(accountDAO.getCode());
                newRole.setRoleId(roleId);

                accountRolesRepository.save(newRole);
            }
        }
        accountService.updateAccount(accountDAO);
        return "redirect:/auth/index";
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping(value ="/updatePassword/{code}")
    public String updatePassword(@PathVariable("code") String code, @RequestParam("newPassword") String newPassword) {
        accountService.updateAccountPassword(code, newPassword);
        return "redirect:/auth/index";
    }

    @PreAuthorize("hasAnyRole('admin', 'qc', 'whManager', 'sale')")
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
