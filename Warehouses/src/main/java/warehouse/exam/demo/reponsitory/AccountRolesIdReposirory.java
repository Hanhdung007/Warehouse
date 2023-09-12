package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.exam.demo.model.AccountsRoles;
import warehouse.exam.demo.model.AccountsRolesId;

public interface AccountRolesIdReposirory extends JpaRepository<AccountsRoles, AccountsRolesId> {

}
