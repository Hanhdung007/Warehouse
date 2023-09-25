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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author LAPTOP123
 */
@Entity
@Table(name = "Orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderCode", query = "SELECT o FROM Orders o WHERE o.orderCode = :orderCode"),
    @NamedQuery(name = "Orders.findByDescription", query = "SELECT o FROM Orders o WHERE o.description = :description"),
    @NamedQuery(name = "Orders.findByCreatedDate", query = "SELECT o FROM Orders o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "Orders.findByAmount", query = "SELECT o FROM Orders o WHERE o.amount = :amount"),
    @NamedQuery(name = "Orders.findByStatus", query = "SELECT o FROM Orders o WHERE o.status = :status"),
    @NamedQuery(name = "Orders.findByDisabled", query = "SELECT o FROM Orders o WHERE o.disabled = :disabled")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Order_Code")
    private String orderCode;
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "Amount")
    private Integer amount;
    @Size(max = 50)
    @Column(name = "Status")
    private String status;
    @Column(name = "Disabled")
    private Boolean disabled;
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    @ManyToOne
    private Customers customerID;
    @JoinColumn(name = "GroupID", referencedColumnName = "GroupID")
    @ManyToOne
    private Groups groupID;
    @JoinColumn(name = "Item_Code", referencedColumnName = "code")
    @ManyToOne
    private Itemdatas itemCode;
    @JoinColumn(name = "UnitID", referencedColumnName = "UnitID")
    @ManyToOne
    private Unit unitID;
    @Column(name = "booked_qty")
    private Double bookQty;
    @Column(name = "shipped_qty")
    private Double shippedQty;

    public Orders() {
    }

    public Orders(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBookQty() {
        return bookQty;
    }

    public void setBookQty(Double bookQty) {
        this.bookQty = bookQty;
    }

    public Double getShippedQty() {
        return shippedQty;
    }

    public void setShippedQty(Double shippedQty) {
        this.shippedQty = shippedQty;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }

    public Groups getGroupID() {
        return groupID;
    }

    public void setGroupID(Groups groupID) {
        this.groupID = groupID;
    }

    public Itemdatas getItemCode() {
        return itemCode;
    }

    public void setItemCode(Itemdatas itemCode) {
        this.itemCode = itemCode;
    }

    public Unit getUnitID() {
        return unitID;
    }

    public void setUnitID(Unit unitID) {
        this.unitID = unitID;
    }

}
