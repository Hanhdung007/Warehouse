package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import warehouse.exam.demo.model.Accounts;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AccountController {
    private final AuthenticationManager authenticationManager;

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
            if(authentication.isAuthenticated()){  
                String username = authentication.getName();
                session.setAttribute("username", username);
                session.setAttribute("loggedInUser", true);
                return "redirect:/itemdata/index";
            }else{
                 model.addAttribute("errorMessage", "Invalid Email Or Password!");
            return "login/login";
            }
        } catch (AuthenticationException ex) {
            model.addAttribute("errorMessage", "Invalid Email Or Password!");
            return "login/login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
