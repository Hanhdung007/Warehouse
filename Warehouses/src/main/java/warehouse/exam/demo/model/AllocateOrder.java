/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DUNG
 */
@Entity
@Table(name = "allocate_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AllocateOrder.findAll", query = "SELECT a FROM AllocateOrder a"),
    @NamedQuery(name = "AllocateOrder.findById", query = "SELECT a FROM AllocateOrder a WHERE a.id = :id"),
    @NamedQuery(name = "AllocateOrder.findByLocationCode", query = "SELECT a FROM AllocateOrder a WHERE a.locationCode = :locationCode"),
    @NamedQuery(name = "AllocateOrder.findByItemMasterId", query = "SELECT a FROM AllocateOrder a WHERE a.itemMasterId = :itemMasterId"),
    @NamedQuery(name = "AllocateOrder.findByQuantity", query = "SELECT a FROM AllocateOrder a WHERE a.quantity = :quantity"),
    @NamedQuery(name = "AllocateOrder.findByCreatedDate", query = "SELECT a FROM AllocateOrder a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "AllocateOrder.findByConfirm", query = "SELECT a FROM AllocateOrder a WHERE a.confirm = :confirm")})
public class AllocateOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "location_code")
    private String locationCode;
    @Column(name = "item_master_id")
    private Integer itemMasterId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "confirm")
    private Boolean confirm;

    public AllocateOrder() {
    }

    public AllocateOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public Integer getItemMasterId() {
        return itemMasterId;
    }

    public void setItemMasterId(Integer itemMasterId) {
        this.itemMasterId = itemMasterId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

}
