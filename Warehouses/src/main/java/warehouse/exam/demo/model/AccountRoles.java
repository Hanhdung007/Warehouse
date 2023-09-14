package warehouse.exam.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account_roles", schema = "dbo", catalog = "Warehouse")
public class AccountRoles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "account_code")
    private String accountCode;
    @Basic
    @Column(name = "roles_id")
    private Integer rolesId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountRoles that = (AccountRoles) o;
        return id == that.id && Objects.equals(accountCode, that.accountCode) && Objects.equals(rolesId, that.rolesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountCode, rolesId);
    }
}
