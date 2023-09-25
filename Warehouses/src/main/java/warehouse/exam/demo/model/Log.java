/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DUNG
 */
@Entity
@Table(name = "log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l"),
    @NamedQuery(name = "Log.findById", query = "SELECT l FROM Log l WHERE l.id = :id"),
    @NamedQuery(name = "Log.findByLocationName", query = "SELECT l FROM Log l WHERE l.locationName = :locationName"),
    @NamedQuery(name = "Log.findBySaveDate", query = "SELECT l FROM Log l WHERE l.saveDate = :saveDate"),
    @NamedQuery(name = "Log.findByQuantity", query = "SELECT l FROM Log l WHERE l.quantity = :quantity"),
    @NamedQuery(name = "Log.findByMethod", query = "SELECT l FROM Log l WHERE l.method = :method")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "locationName")
    private String locationName;
    @Column(name = "save_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saveDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity")
    private Double quantity;
    @Size(max = 100)
    @Column(name = "method")
    private String method;
    @JoinColumn(name = "itemmaster_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private Itemmasters itemmasterId;

    public Log() {
    }

    public Log(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Itemmasters getItemmasterId() {
        return itemmasterId;
    }

    public void setItemmasterId(Itemmasters itemmasterId) {
        this.itemmasterId = itemmasterId;
    }
    
}
