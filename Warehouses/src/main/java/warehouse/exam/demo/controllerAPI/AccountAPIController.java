package warehouse.exam.demo.controllerAPI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.reponsitory.AccountRepository;
import warehouse.exam.demo.service.AccountService;
import warehouse.exam.demo.util.JwtTokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountAPIController {
    protected final Log logger = LogFactory.getLog(getClass());

    final AuthenticationManager authenticationManager;
    final AccountService accountService;
    final JwtTokenUtil jwtTokenUtil;

    public AccountAPIController(AuthenticationManager authenticationManager,
                                AccountService accountService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody AccountDAO accountDAO) {
        Map<String, Object> responseMap = new HashMap<>();
        String email = accountDAO.getEmail();
        String password = accountDAO.getPassword();
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email
                    , password));
            if (auth.isAuthenticated()) {
                logger.info("Logged In");
                UserDetails userDetails = accountService.loadUserByUsername(email);
                String token = jwtTokenUtil.generateToken(userDetails);
                responseMap.put("error", false);
                responseMap.put("message", "Logged In");
                responseMap.put("token", token);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping("/api/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> logout(HttpServletResponse response, HttpServletRequest request) {
        // Đăng xuất khỏi phiên làm việc trên máy chủ
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Xóa cookie JSESSIONID
        Cookie cookie = new Cookie("JSESSIONID", null);
        String contextPath = request.getContextPath();
        String cookiePath = StringUtils.hasText(contextPath) ? contextPath : "/";
        cookie.setPath(cookiePath);
        cookie.setMaxAge(0);
        cookie.setSecure(request.isSecure());
        response.addCookie(cookie);
        // Xóa token
        JwtTokenUtil.CookieUtil.clearToken(response);
        return ResponseEntity.ok("Logged out successfully!");
    }
}
