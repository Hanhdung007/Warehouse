package warehouse.exam.demo.DAL;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Supplier;

/**
 *
 * @author DUNG
 */

@NonNull
@AllArgsConstructor
public class importDAO {

    private Integer id;
    private String driver;
    private String driversPhone;
    private String dateImport;
    private String note;
    private Boolean status;
    private String supplierName;
    private String supId;

    public importDAO(Importorders importOrder) {
        this.id = importOrder.getId();
        this.driver = importOrder.getDriver();
        this.driversPhone = importOrder.getDriversPhone();
        this.dateImport = importOrder.getDateImport();
        this.note = importOrder.getNote();
        this.status = importOrder.getStatus();
        this.supplierName = importOrder.getSupId().getSupName();
    }

    public importDAO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriversPhone() {
        return driversPhone;
    }

    public void setDriversPhone(String driversPhone) {
        this.driversPhone = driversPhone;
    }

    public String getDateImport() {
        return dateImport;
    }

    public void setDateImport(String dateImport) {
        this.dateImport = dateImport;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }
}
