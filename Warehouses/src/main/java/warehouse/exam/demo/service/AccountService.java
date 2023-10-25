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
import warehouse.exam.demo.model.CustomUserDetails;
import warehouse.exam.demo.config.CodeGenerator;
import warehouse.exam.demo.model.Roles;
import warehouse.exam.demo.reponsitory.AccountRepository;
import warehouse.exam.demo.reponsitory.AccountRolesRepository;
import warehouse.exam.demo.reponsitory.ImportRepository;
import warehouse.exam.demo.reponsitory.RolesRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    AccountRolesRepository accountRolesRepository;

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
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Accounts account = accountsRepository.findAccountsByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Collection<? extends GrantedAuthority> authorities = account.getAuthorities();

        return new CustomUserDetails(account.getEmail(), account.getPassword(), authorities, account.getName());
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

    public String saveAccount(@ModelAttribute AccountDAO accountDAO) {
        Accounts newAccounts = new Accounts();
        String generateCode = CodeGenerator.generateRandomCode();
        newAccounts.setCode(generateCode);
        newAccounts.setName(accountDAO.getName());
        newAccounts.setEmail(accountDAO.getEmail());
        newAccounts.setPassword(passwordEncoder.encode(accountDAO.getPassword()));
        newAccounts.setPhone(accountDAO.getPhone());
        newAccounts.setIsActive(accountDAO.getIsActive());
        accountsRepository.save(newAccounts);
        return generateCode;
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
                return "Receive Inventory";
            case 4:
                return "Warehouse Manager";
            case 5:
                return "Quantity Control";
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
