package warehouse.exam.demo.controllerAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.service.AccountService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountAPIController {
    final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    public AccountAPIController(AccountService accountService, AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> loginUser(HttpSession session, @RequestBody AccountDAO accountDAO) {
        Map<String, Object> responseMap = new HashMap<>();
        String email = accountDAO.getEmail();
        String password = accountDAO.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            if (authentication.isAuthenticated()) {
                // Lưu thông tin người dùng vào phiên làm việc (session)
                String username = authentication.getName();
                session.setAttribute("userDetails", authentication);
                responseMap.put("error", false);
                responseMap.put("message", "Logged In");
                responseMap.put("username", username);
                return ResponseEntity.ok(responseMap);
            }
        } catch (AuthenticationException e) {
            // Lỗi khác, trả về mã lỗi 401
            responseMap.put("error", true);
            responseMap.put("message", "Invalid email or password!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
        }
        return null;
    }

    @GetMapping("/index")
    public ResponseEntity<List<AccountDAO>> index() {
        return ResponseEntity.ok(accountService.findAll());
    }
}
