package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import warehouse.exam.demo.model.Accounts;
import warehouse.exam.demo.model.Roles;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findAllById(int roleCode);

    @Query("SELECT r FROM Roles r JOIN r.accountsRolesById a WHERE a.accountCode = :accountCode")
    List<Roles> findByAccounts(@Param("accountCode") String accountCode);
}
