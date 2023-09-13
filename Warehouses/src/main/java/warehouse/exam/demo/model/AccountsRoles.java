package warehouse.exam.demo.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "accounts_roles", schema = "dbo", catalog = "Warehouse")
public class AccountsRoles implements Serializable {
    @Id
    @Column(name = "account_code")
    private String accountCode;
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @ManyToOne
    @JoinColumn(name = "account_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Accounts accountsByAccountCode;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Roles rolesByRolesId;

    public AccountsRoles() {
    }

    public AccountsRoles(String accountCode, Integer roleId) {
        this.accountCode = accountCode;
        this.roleId = roleId;
    }

    public String getAccountCode(){
        return accountCode;
    }
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Accounts getAccountsByAccountCode() {
        return accountsByAccountCode;
    }

    public void setAccountsByAccountCode(Accounts accountsByAccountCode) {
        this.accountsByAccountCode = accountsByAccountCode;
    }
    public Roles getRolesByRolesId(){
        return rolesByRolesId;
    }
    public void setRolesByRolesId(Roles rolesByRolesId){
        this.rolesByRolesId = rolesByRolesId;
    }
}
