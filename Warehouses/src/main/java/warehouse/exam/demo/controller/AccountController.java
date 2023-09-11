package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.DAL.itemdataDAO;
import warehouse.exam.demo.model.Accounts;

import javax.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Itemdatas;
import warehouse.exam.demo.service.AccountService;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            String username = authentication.getName();
            session.setAttribute("username", username);
            session.setAttribute("loggedInUser", true);
            return "redirect:/itemdata/index";
        } catch (AuthenticationException ex) {
            model.addAttribute("errorMessage", "Invalid Email Or Password!");
            return "login/login";
        }
    }

    @GetMapping("/index")
    public String index(Model model) {
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
    public String update(@ModelAttribute AccountDAO accountDAO, @PathVariable("code") String code) throws IOException {
        accountService.updateAccount(accountDAO, code);
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
