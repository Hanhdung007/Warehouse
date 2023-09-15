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
 * @author DUNG
 */
@Entity
@Table(name = "issue_orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IssueOrders.findAll", query = "SELECT i FROM IssueOrders i"),
    @NamedQuery(name = "IssueOrders.findById", query = "SELECT i FROM IssueOrders i WHERE i.id = :id"),
    @NamedQuery(name = "IssueOrders.findByIssueDated", query = "SELECT i FROM IssueOrders i WHERE i.issueDated = :issueDated"),
    @NamedQuery(name = "IssueOrders.findByIssueReason", query = "SELECT i FROM IssueOrders i WHERE i.issueReason = :issueReason"),
    @NamedQuery(name = "IssueOrders.findBySubmitBy", query = "SELECT i FROM IssueOrders i WHERE i.submitBy = :submitBy"),
    @NamedQuery(name = "IssueOrders.findByIssueActive", query = "SELECT i FROM IssueOrders i WHERE i.issueActive = :issueActive"),
    @NamedQuery(name = "IssueOrders.findByItemCode", query = "SELECT i FROM IssueOrders i WHERE i.itemCode = :itemCode"),
    @NamedQuery(name = "IssueOrders.findByQtyExport", query = "SELECT i FROM IssueOrders i WHERE i.qtyExport = :qtyExport"),
    @NamedQuery(name = "IssueOrders.findByQtyActualExport", query = "SELECT i FROM IssueOrders i WHERE i.qtyActualExport = :qtyActualExport")})
public class IssueOrders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "issue_dated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDated;
    @Size(max = 255)
    @Column(name = "issue_reason")
    private String issueReason;
    @Size(max = 255)
    @Column(name = "submit_by")
    private String submitBy;
    @Column(name = "issue_active")
    private Boolean issueActive;
    @Size(max = 255)
    @Column(name = "item_code")
    private String itemCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qty_export")
    private Double qtyExport;
    @Column(name = "qty_actual_export")
    private Double qtyActualExport;
    @JoinColumn(name = "itemmaster_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Itemmasters itemmasterId;

    public IssueOrders() {
    }

    public IssueOrders(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getIssueDated() {
        return issueDated;
    }

    public void setIssueDated(Date issueDated) {
        this.issueDated = issueDated;
    }

    public String getIssueReason() {
        return issueReason;
    }

    public void setIssueReason(String issueReason) {
        this.issueReason = issueReason;
    }

    public String getSubmitBy() {
        return submitBy;
    }

    public void setSubmitBy(String submitBy) {
        this.submitBy = submitBy;
    }

    public Boolean getIssueActive() {
        return issueActive;
    }

    public void setIssueActive(Boolean issueActive) {
        this.issueActive = issueActive;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getQtyExport() {
        return qtyExport;
    }

    public void setQtyExport(Double qtyExport) {
        this.qtyExport = qtyExport;
    }

    public Double getQtyActualExport() {
        return qtyActualExport;
    }

    public void setQtyActualExport(Double qtyActualExport) {
        this.qtyActualExport = qtyActualExport;
    }

    public Itemmasters getItemmasterId() {
        return itemmasterId;
    }

    public void setItemmasterId(Itemmasters itemmasterId) {
        this.itemmasterId = itemmasterId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IssueOrders)) {
            return false;
        }
        IssueOrders other = (IssueOrders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse.exam.demo.model.IssueOrders[ id=" + id + " ]";
    }
    
}
