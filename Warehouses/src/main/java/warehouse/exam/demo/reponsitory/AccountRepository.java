package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.exam.demo.model.Accounts;

public interface AccountRepository extends JpaRepository<Accounts, String> {
    Accounts findAccountsByEmail(String email);
}
