/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DUNG
 */
@Entity
@Table(name = "itemdatas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemdata.findAll", query = "SELECT i FROM Itemdata i"),
    @NamedQuery(name = "Itemdata.findByCode", query = "SELECT i FROM Itemdata i WHERE i.code = :code"),
    @NamedQuery(name = "Itemdata.findByName", query = "SELECT i FROM Itemdata i WHERE i.name = :name"),
    @NamedQuery(name = "Itemdata.findByColor", query = "SELECT i FROM Itemdata i WHERE i.color = :color"),
    @NamedQuery(name = "Itemdata.findByType", query = "SELECT i FROM Itemdata i WHERE i.type = :type"),
    @NamedQuery(name = "Itemdata.findByActive", query = "SELECT i FROM Itemdata i WHERE i.active = :active"),
    @NamedQuery(name = "Itemdata.findByImage", query = "SELECT i FROM Itemdata i WHERE i.image = :image")})
public class Itemdata implements Serializable {

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
    @Size(max = 255)
    @Column(name = "color")
    private String color;
    @Size(max = 255)
    @Column(name = "type")
    private String type;
    @Column(name = "active")
    private Boolean active;
    @Size(max = 255)
    @Column(name = "image")
    private String image;

    public Itemdata() {
    }

    public Itemdata(String code) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
