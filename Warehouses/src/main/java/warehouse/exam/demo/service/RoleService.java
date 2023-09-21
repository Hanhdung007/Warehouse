package warehouse.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.model.Accounts;
import warehouse.exam.demo.model.Roles;
import warehouse.exam.demo.reponsitory.RolesRepository;

import java.util.List;

@Service
public class RoleService {
    private final RolesRepository roleRepository;

    @Autowired
    public RoleService(RolesRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Roles> getRolesByAccount(Accounts account) {
        return roleRepository.findByAccounts(account.getAuthorities().toString());
    }
}
