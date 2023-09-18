package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.exam.demo.model.AccountRolesId;
import warehouse.exam.demo.model.AccountsRoles;

import java.util.List;

public interface AccountRolesRepository extends JpaRepository<AccountsRoles, AccountRolesId> {
    List<AccountsRoles> findAllByAccountCode(String accountCode);

//    AccountsRoles findByRolesId(Integer roleIds);
}
