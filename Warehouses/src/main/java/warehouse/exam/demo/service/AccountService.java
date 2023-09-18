package warehouse.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.model.Accounts;
import warehouse.exam.demo.model.Roles;
import warehouse.exam.demo.reponsitory.AccountRepository;
import warehouse.exam.demo.reponsitory.RolesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    @Lazy
    private final BCryptPasswordEncoder passwordEncoder;
    private final AccountRepository accountsRepository;
    private final RoleService roleService;

    @Autowired
    public AccountService(BCryptPasswordEncoder passwordEncoder, AccountRepository accountRepository, RolesRepository rolesRepository, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.accountsRepository = accountRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Accounts account = accountsRepository.findAccountsByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        List<Roles> roles = roleService.getRolesByAccount(account);

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getId()));
        }

        return new User(account.getEmail(), account.getPassword(), authorities);
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
//            acc.setAccountCode(String.join(", ", list.getAccountsRolesById().stream().map(AccountsRoles::getAccountCode).toList()));
            acc.setAccountCode(String.join(" | ", list.getAccountsRolesById().stream().map(accountsRoles
                    -> accountsRoles.getRolesByRolesId().getRoleName()).toList()));
            dao.add(acc);
        }
        return dao;
    }

    public List<AccountDAO> searchAllAccount(String keyword) {
        return accountsRepository.searchAllAccount(keyword);
    }

    public Accounts saveAccount(@ModelAttribute AccountDAO accountDAO) {
        Accounts newAccounts = new Accounts();
        newAccounts.setCode(accountDAO.getCode());
        newAccounts.setName(accountDAO.getName());
        newAccounts.setEmail(accountDAO.getEmail());
        newAccounts.setPassword(passwordEncoder.encode(accountDAO.getPassword()));
        newAccounts.setPhone(accountDAO.getPhone());
        newAccounts.setIsActive(accountDAO.getIsActive());
        return accountsRepository.save(newAccounts);
    }
    public Accounts findOne(String code) {
        return accountsRepository.findById(code).get();
    }

    public void updateAccountPassword(String accountCode, String newPassword) {
        Optional<Accounts> optionalAccount = accountsRepository.findById(accountCode);
        if (optionalAccount.isPresent()) {
            Accounts acc = optionalAccount.get();
            acc.setPassword(passwordEncoder.encode(newPassword));
            accountsRepository.save(acc);
        }
    }

    public static String getRoleNameById(int roleId) {
        // Ánh xạ roleId thành tên vai trò tương ứng
        switch (roleId) {
            case 1:
                return "Admin";
            case 2:
                return "Sale Order";
            case 3:
                return "Quantity Control";
            case 4:
                return "Warehouse Manager";
            default:
                return "Unknown Role";
        }
    }

    public Accounts updateAccount(AccountDAO dao) {
        Optional<Accounts> accounts = accountsRepository.findById(dao.getCode());
        if (accounts.isPresent()) {
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
            return accountsRepository.save(acc);
        } else{
            throw new RuntimeException("Can not find account with = " + dao.getCode());
        }
    }
}
