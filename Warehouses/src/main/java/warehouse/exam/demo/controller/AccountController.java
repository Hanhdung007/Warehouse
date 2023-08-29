package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.model.Accounts;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AccountController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("account", new Accounts());
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        AccountDAO accountDAO = AccountDAO.builder()
                .email(email)
                .password(password)
                .build();
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "http://localhost:9999/api/login",
                    accountDAO,
                    Map.class);
            Map<String, Object> responseBody = response.getBody();
            boolean isError = (boolean) responseBody.get("error");
            String message = (String) responseBody.get("message");
            if (!isError) {
                // Xử lý thành công - Thêm thông tin vào model và chuyển hướng
                httpSession.setAttribute("isAuthenticated", true);
                return "redirect:/itemdata/index";
            } else {
                // Xử lý thất bại - Thêm thông tin lỗi vào model và hiển thị lại form đăng nhập
                model.addAttribute("errorMessage", message);
                return "login/login";
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // Xử lý lỗi đăng nhập không hợp lệ (401 Unauthorized)
                model.addAttribute("errorMessage", "Invalid Email Or Password!");
                return "login/login";
            } else if (ex.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                // Xử lý lỗi máy chủ (500 Internal Server Error)
                model.addAttribute("errorMessage", "Server Error!");
                return "login/login";
            }
        }
        return null;
    }
}
