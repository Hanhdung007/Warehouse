/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.DAL;

/**
 *
 * @author DUNG
 */
public class importDAO {

    private Integer id;
    private String driver;
    private String driversPhone;
    private String factory;
    private String dateImport;
    private String note;
    private Boolean status;

    public importDAO() {
    }

    public importDAO(Integer id, String driver, String driversPhone, String factory, String dateImport, String note, Boolean status) {
        this.id = id;
        this.driver = driver;
        this.driversPhone = driversPhone;
        this.factory = factory;
        this.dateImport = dateImport;
        this.note = note;
        this.status = status;
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

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
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
    
}
