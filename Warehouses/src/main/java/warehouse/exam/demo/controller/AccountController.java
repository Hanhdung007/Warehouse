package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import warehouse.exam.demo.model.Accounts;

@Controller
@RequestMapping("/auth")
public class AccountController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("account", new Accounts());
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        ResponseEntity<?> response = restTemplate.postForEntity(
                "http://localhost:9999/auth/login",
                null,
                String.class,
                email, password);
        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("successMessage", "Login successful!");
            return "redirect:/itemdata/index";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login/login";
        }
    }

}
