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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<AccountDAO> findAll() {
        List<AccountDAO> dao = new ArrayList<>();
        List<Accounts> accounts = accountsRepository.findAll();
        for (Accounts list : accounts) {
            AccountDAO acc = new AccountDAO();
            acc.setCode(list.getCode());
            acc.setName(list.getName());
            acc.setEmail(list.getEmail());
            acc.setPhone(list.getPhone());
            acc.setIsActive(list.getIsActive());
            dao.add(acc);
        }
        return dao;
    }

    public List<AccountDAO> searchAllAccount(String keyword) {
        return accountsRepository.searchAllAccount(keyword);
    }

    public Accounts saveAccount(AccountDAO accountDAO) {
        Accounts newAccounts = new Accounts();
        newAccounts.setCode(accountDAO.getCode());
        newAccounts.setName(accountDAO.getName());
        newAccounts.setEmail(accountDAO.getEmail());
        newAccounts.setPassword(accountDAO.getPassword());
        newAccounts.setPhone(accountDAO.getPhone());
        newAccounts.setIsActive(accountDAO.getIsActive());
//        newAccounts.setRole(newAccounts.getRole());
        return accountsRepository.save(newAccounts);
    }

    public Accounts findOne(String code) {
        return accountsRepository.findById(code).get();
    }

    @Transactional
    public void updateAccountPassword(String accountCode, String newPassword) {
        Optional<Accounts> optionalAccount = accountsRepository.findById(accountCode);
        if (optionalAccount.isPresent()) {
            Accounts acc = optionalAccount.get();
            acc.setPassword(newPassword);
            accountsRepository.save(acc);
        }
    }

    public void updateAccount(AccountDAO dao) {
        Optional<Accounts> accounts = accountsRepository.findById(dao.getCode());
        if (!accounts.isPresent()) {
            Accounts acc = accounts.get();
            if (dao.getCode() != null) {
                acc.setCode(dao.getCode());
            }
            if (dao.getName() != null) {
                acc.setName(dao.getName());
            }
            if (dao.getEmail() != null) {
                acc.setEmail(dao.getEmail());
            }
            if (dao.getPhone() != null) {
                acc.setPhone(dao.getPhone());
            }
            if (dao.getIsActive() != null) {
                acc.setIsActive(dao.getIsActive());
            }
            accountsRepository.save(acc);
        }
    }
}
