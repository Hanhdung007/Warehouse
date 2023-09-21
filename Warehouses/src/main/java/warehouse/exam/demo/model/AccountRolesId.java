package warehouse.exam.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
@Getter
@Setter
public class AccountRolesId implements Serializable {
    @Column(name = "account_code", insertable = false, updatable = false)
    private String accountCode;

    @Column(name = "role_id", insertable = false, updatable = false)
    private Integer roleId;
}
