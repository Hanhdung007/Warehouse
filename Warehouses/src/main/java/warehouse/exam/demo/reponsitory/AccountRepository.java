package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import warehouse.exam.demo.DAL.AccountDAO;
import warehouse.exam.demo.model.Accounts;

import java.util.List;

public interface AccountRepository extends JpaRepository<Accounts, String> {
    Accounts findAccountsByEmail(String email);

    Accounts findAccountsByCode(String code);

    @Query("SELECT NEW warehouse.exam.demo.DAL.AccountDAO(account) FROM Accounts account WHERE account.code LIKE %:keyword% OR account.name LIKE %:keyword% OR account.email LIKE %:keyword% OR account.phone LIKE %:keyword% OR account.roles")
    List<AccountDAO> searchAllAccount(@Param("keyword") String keyword);
}
