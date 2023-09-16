package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.exam.demo.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findAllById(int roleCode);
}
