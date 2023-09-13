package warehouse.exam.demo.controllerAPI;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.model.Accounts;
import warehouse.exam.demo.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountAPIController {
    final AccountService accountService;

    public AccountAPIController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody AccountDAO accountDAO,
                                                         HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();
        String email = accountDAO.getEmail();
        String password = accountDAO.getPassword();
        try {
            // Thực hiện xác thực bằng email và password
            UserDetails userDetails = accountService.loadUserByUsername(email);
            // Kiểm tra mật khẩu
            if (password.equals(userDetails.getPassword())) {
                // Lưu thông tin người dùng vào phiên làm việc (session)
                HttpSession session = request.getSession();
                session.setAttribute("userDetails", userDetails);
                responseMap.put("error", false);
                responseMap.put("message", "Logged In");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (UsernameNotFoundException e) {
            responseMap.put("error", true);
            responseMap.put("message", "User Not Found!");
            return ResponseEntity.status(401).body(responseMap);
        }
    }

    @GetMapping("/index")
    public ResponseEntity<List<AccountDAO>> index() {
        return ResponseEntity.ok(accountService.findAll());
    }

//    @GetMapping("/index/{code}")
//    public EntityModel<Accounts> details(@PathVariable String code){
//        Accounts accounts = accountService.findOne(code);
//        if(accounts == null){
//            throw new RuntimeException("Account Is Not Found!" + code);
//        }
//        return EntityModel.of(accounts.getName(), accounts.getEmail());
//    }
}
