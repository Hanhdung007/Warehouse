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
import org.springframework.web.bind.annotation.*;
import warehouse.exam.demo.reponsitory.AccountRepository;
import warehouse.exam.demo.service.AccountService;
import warehouse.exam.demo.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountAPIController {
    protected final Log logger = LogFactory.getLog(getClass());

    final AuthenticationManager authenticationManager;
    final AccountService accountService;
    final JwtTokenUtil jwtTokenUtil;

    public AccountAPIController(AccountRepository accountRepository, AuthenticationManager authenticationManager,
                                AccountService accountService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> loginUser(@RequestParam("email") String email,
                                       @RequestParam("password") String password) {
        Map<String, Object> responseMap = new HashMap<>();
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

//    @PostMapping("/register")
//    public ResponseEntity<?> saveUser(@RequestParam("name") String name,
//                                      @RequestParam("last_name") String lastName,
//                                      @RequestParam("user_name") String userName, @RequestParam("email") String email
//            , @RequestParam("password") String password) {
//        Map<String, Object> responseMap = new HashMap<>();
//        User user = new User();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setPassword(new BCryptPasswordEncoder().encode(password));
//        user.setRole("USER");
//        user.setUserName(userName);
//        UserDetails userDetails = accountService.loadUserByUsername(userName);
//        String token = jwtTokenUtil.generateToken(userDetails);
//        userRepository.save(user);
//        responseMap.put("error", false);
//        responseMap.put("username", userName);
//        responseMap.put("message", "Account created successfully");
//        responseMap.put("token", token);
//        return ResponseEntity.ok(responseMap);
//    }
}
