package warehouse.exam.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accounts_roles", schema = "dbo", catalog = "Warehouse")
public class AccountsRoles {
    @Id
    @Column(name = "account_code")
    private String accountCode;
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @ManyToOne
    @JoinColumn(name = "account_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Accounts accountsByAccountCode;
    public String getAccountCode() {
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
}
