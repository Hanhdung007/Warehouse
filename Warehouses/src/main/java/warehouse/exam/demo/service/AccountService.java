package warehouse.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.model.Accounts;
import warehouse.exam.demo.reponsitory.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountsRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountsRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Accounts account = accountsRepository.findAccountsByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
//        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        return new User(account.getEmail(), account.getPassword(), authorityList);
    }

//    @Autowired
//    public void findByName(String name){
//        accountsRepository.findAccountByName(name);
//    }
}
