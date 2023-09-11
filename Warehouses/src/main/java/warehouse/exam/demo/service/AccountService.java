package warehouse.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.AccountDAO;
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

    //    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Accounts account = accountsRepository.findAccountsByEmail(email);
//        if (account == null) {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//        List<GrantedAuthority> authorityList = new ArrayList<>();
////        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
//        return new User(account.getEmail(), account.getPassword(), authorityList);
//    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Accounts account = accountsRepository.findAccountsByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        return new User(account.getName(), account.getPassword(), authorityList);
    }

//    public List<AccountDAO> findAll() {
//        List<AccountDAO> dao = new ArrayList<>();
//        List<Accounts> accounts = accountsRepository.findAll();
//        for (Accounts list : accounts) {
//            AccountDAO acc = new AccountDAO();
//            acc.setCode(list.getCode());
//            acc.setName(list.getName());
//            acc.setEmail(list.getEmail());
//            acc.setPhone(list.getPhone());
//            acc.setIsActive(list.getIsActive());
//            dao.add(acc);
//        }
//        return dao;
//    }
}
