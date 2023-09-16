package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.exam.demo.model.AccountRolesId;
import warehouse.exam.demo.model.AccountsRoles;

public interface AccountRolesRepository extends JpaRepository<AccountsRoles, AccountRolesId> {
    AccountsRoles findAllByIdAccountCode(String accountCode);
}
