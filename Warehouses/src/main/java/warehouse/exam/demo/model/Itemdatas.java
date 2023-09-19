/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "itemdatas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemdatas.findAll", query = "SELECT i FROM Itemdatas i"),
    @NamedQuery(name = "Itemdatas.findByCode", query = "SELECT i FROM Itemdatas i WHERE i.code = :code"),
    @NamedQuery(name = "Itemdatas.findByName", query = "SELECT i FROM Itemdatas i WHERE i.name = :name"),
    @NamedQuery(name = "Itemdatas.findByColor", query = "SELECT i FROM Itemdatas i WHERE i.color = :color"),
    @NamedQuery(name = "Itemdatas.findByType", query = "SELECT i FROM Itemdatas i WHERE i.type = :type"),
    @NamedQuery(name = "Itemdatas.findByActive", query = "SELECT i FROM Itemdatas i WHERE i.active = :active"),
    @NamedQuery(name = "Itemdatas.findByImage", query = "SELECT i FROM Itemdatas i WHERE i.image = :image")})
public class Itemdatas implements Serializable {

    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "color")
    private String color;
    @Size(max = 255)
    @Column(name = "type")
    private String type;
    @Size(max = 255)
    @Column(name = "image")
    private String image;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code")
    private String code;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "codeItemdata")
    private List<Itemmasters> itemmastersList;
    public Itemdatas() {
    }

    public Itemdatas(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    public List<Itemmasters> getItemmastersList() {
        return itemmastersList;
    }

    public void setItemmastersList(List<Itemmasters> itemmastersList) {
        this.itemmastersList = itemmastersList;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
