/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DUNG
 */
@Entity
@Table(name = "warehouses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warehouses.findAll", query = "SELECT w FROM Warehouses w"),
    @NamedQuery(name = "Warehouses.findByCode", query = "SELECT w FROM Warehouses w WHERE w.code = :code"),
    @NamedQuery(name = "Warehouses.findByName", query = "SELECT w FROM Warehouses w WHERE w.name = :name"),
    @NamedQuery(name = "Warehouses.findByActive", query = "SELECT w FROM Warehouses w WHERE w.active = :active")})
public class Warehouses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "warehouseCode")
    private List<Locations> locationsList;

    public Warehouses() {
    }

    public Warehouses(String code) {
        this.code = code;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    public List<Locations> getLocationsList() {
        return locationsList;
    }

    public void setLocationsList(List<Locations> locationsList) {
        this.locationsList = locationsList;
    }
}
