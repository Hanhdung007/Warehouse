/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.DAL;

import java.util.List;
import warehouse.exam.demo.model.Locations;

/**
 *
 * @author DUNG
 */
public class warehouseDAO {
    private String code;
    private String name;
    private boolean active;
    private double totalCapacity;
    private double totalRemain;
    private double totalLocation;
    
    private List<Locations> locations;

    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }

    public double getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(double totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public double getTotalRemain() {
        return totalRemain;
    }

    public void setTotalRemain(double totalRemain) {
        this.totalRemain = totalRemain;
    }

    public double getTotalLocation() {
        return totalLocation;
    }

    public void setTotalLocation(double totalLocation) {
        this.totalLocation = totalLocation;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
