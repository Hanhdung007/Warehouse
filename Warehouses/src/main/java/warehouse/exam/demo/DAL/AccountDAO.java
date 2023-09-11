package warehouse.exam.demo.DAL;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import warehouse.exam.demo.model.Accounts;

@AllArgsConstructor
@Data
@Builder
public class AccountDAO {
    private String code;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Boolean isActive;

    public AccountDAO(Accounts accounts) {
        this.code = accounts.getCode();
        this.name = accounts.getName();
        this.email = accounts.getEmail();
        this.phone = accounts.getPhone();
        this.isActive = accounts.getIsActive();
//        this.role = accounts.getRole();
    }

    public AccountDAO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
