/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.DAL;

import java.util.List;
import javax.validation.constraints.NotNull;
import warehouse.exam.demo.model.Itemmasters;

/**
 *
 * @author DUNG
 */
public class locationDAO {
    @NotNull(message = "code can not be null")
    private String code;
    @NotNull(message = "name can not be null")
    private String name;
    private String warehouseName;
    @NotNull(message = "capacity can not be null")
    private double capacity;
    private double remain;
    private boolean active;
    private String warehouseCode;
    private List<itemmasterDAO> items;

    public List<itemmasterDAO> getItems() {
        return items;
    }

    public void setItems(List<itemmasterDAO> items) {
        this.items = items;
    }
    

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public double getRemain() {
        return remain;
    }

    public void setRemain(double remain) {
        this.remain = remain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
