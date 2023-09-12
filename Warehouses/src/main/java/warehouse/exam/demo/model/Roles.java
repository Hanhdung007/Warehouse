package warehouse.exam.demo.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "role_name")
    private String roleName;
    @OneToMany(mappedBy = "rolesByRoleId")
    private Collection<AccountsRoles> accountsRolesById;
    @ManyToMany(mappedBy = "roles")
    private Set<Accounts> accounts = new HashSet<>();

    public Set<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Accounts> accounts) {
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return id == roles.id && Objects.equals(roleName, roles.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    public Collection<AccountsRoles> getAccountsRolesById() {
        return accountsRolesById;
    }

    public void setAccountsRolesById(Collection<AccountsRoles> accountsRolesById) {
        this.accountsRolesById = accountsRolesById;
    }
}
