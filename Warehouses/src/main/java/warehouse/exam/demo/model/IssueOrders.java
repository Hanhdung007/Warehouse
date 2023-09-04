/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "IssueOrders.findByIssueActive", query = "SELECT i FROM IssueOrders i WHERE i.issueActive = :issueActive")})
public class IssueOrders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "issue_dated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDated;
    @Size(max = 255)
    @Column(name = "issue_reason")
    private String issueReason;
    @Size(max = 255)
    @Column(name = "submitBy")
    private String submitBy;
    @Column(name = "issue_active")
    private Boolean issueActive;
    @OneToMany(mappedBy = "idissueOrder")
    private List<IssueOrderDetails> issueOrderDetailsList;

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

    @XmlTransient
    public List<IssueOrderDetails> getIssueOrderDetailsList() {
        return issueOrderDetailsList;
    }

    public void setIssueOrderDetailsList(List<IssueOrderDetails> issueOrderDetailsList) {
        this.issueOrderDetailsList = issueOrderDetailsList;
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
