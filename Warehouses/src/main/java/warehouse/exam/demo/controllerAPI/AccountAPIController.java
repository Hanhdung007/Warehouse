package warehouse.exam.demo.controllerAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.model.Accounts;
import warehouse.exam.demo.model.CustomUserDetails;
import warehouse.exam.demo.service.AccountService;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountAPIController {
    final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AccountAPIController(AccountService accountService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

//    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Map<String, Object>> loginUser(HttpSession session, @RequestBody AccountDAO accountDAO) {
//        Map<String, Object> responseMap = new HashMap<>();
//        String email = accountDAO.getEmail();
//        String password = accountDAO.getPassword();
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(email, password)
//            );
//            if (authentication.isAuthenticated()) {
//                // Lưu thông tin người dùng vào phiên làm việc (session)
//                String username = authentication.getName();
//                session.setAttribute("userDetails", authentication);
//                responseMap.put("error", false);
//                responseMap.put("message", "Logged In");
//                responseMap.put("username", username);
//                return ResponseEntity.ok(responseMap);
//            }
//        } catch (AuthenticationException e) {
//            // Lỗi khác, trả về mã lỗi 401
//            responseMap.put("error", true);
//            responseMap.put("message", "Invalid email or password!");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
//        }
//        return null;
//    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> loginUser(HttpSession session, @RequestBody AccountDAO accountDAO) {
        Map<String, Object> responseMap = new HashMap<>();
        String email = accountDAO.getEmail();
        String password = accountDAO.getPassword();

        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authResult = authenticationManager.authenticate(authentication);

            // Xác thực thành công, lưu thông tin người dùng vào session
            CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();
                session.setAttribute("userDetails", userDetails);


            responseMap.put("error", false);
                responseMap.put("message", "Logged In");
                responseMap.put("username", userDetails.getUsername());
                return ResponseEntity.ok(responseMap);
        } catch (AuthenticationException e) {
            // Lỗi xác thực, trả về mã lỗi 401
            responseMap.put("error", true);
            responseMap.put("message", "Invalid email or password!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
        }
    }


    @GetMapping("/auth/index")
    public ResponseEntity<List<AccountDAO>> index() {
        return ResponseEntity.ok(accountService.findAll());
    }
}
