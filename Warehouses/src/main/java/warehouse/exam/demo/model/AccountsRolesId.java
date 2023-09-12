package warehouse.exam.demo.model;

import java.io.Serializable;
import java.util.Objects;

public class AccountsRolesId implements Serializable {
    private String accountCode;
    private Integer roleId;

    public AccountsRolesId() {
    }

    public AccountsRolesId(String accountCode, Integer roleId) {
        this.accountCode = accountCode;
        this.roleId = roleId;
    }

    // Getter và Setter

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsRolesId that = (AccountsRolesId) o;
        return Objects.equals(accountCode, that.accountCode) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountCode, roleId);
    }
}

