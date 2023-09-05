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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "issue_order_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IssueOrderDetails.findAll", query = "SELECT i FROM IssueOrderDetails i"),
    @NamedQuery(name = "IssueOrderDetails.findById", query = "SELECT i FROM IssueOrderDetails i WHERE i.id = :id"),
    @NamedQuery(name = "IssueOrderDetails.findByItemCode", query = "SELECT i FROM IssueOrderDetails i WHERE i.itemCode = :itemCode"),
    @NamedQuery(name = "IssueOrderDetails.findByIdItemmaster", query = "SELECT i FROM IssueOrderDetails i WHERE i.idItemmaster = :idItemmaster"),
    @NamedQuery(name = "IssueOrderDetails.findByQuantityExport", query = "SELECT i FROM IssueOrderDetails i WHERE i.quantityExport = :quantityExport"),
    @NamedQuery(name = "IssueOrderDetails.findByQuantityActualexport", query = "SELECT i FROM IssueOrderDetails i WHERE i.quantityActualexport = :quantityActualexport")})
public class IssueOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "itemCode")
    private String itemCode;
    @Column(name = "id_itemmaster")
    private Integer idItemmaster;
    @Column(name = "quantityExport")
    private Integer quantityExport;
    @Column(name = "quantityActualexport")
    private Integer quantityActualexport;
    @JoinColumn(name = "idissue_order", referencedColumnName = "id")
    @ManyToOne
    private IssueOrders idissueOrder;

    public IssueOrderDetails() {
    }

    public IssueOrderDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getIdItemmaster() {
        return idItemmaster;
    }

    public void setIdItemmaster(Integer idItemmaster) {
        this.idItemmaster = idItemmaster;
    }

    public Integer getQuantityExport() {
        return quantityExport;
    }

    public void setQuantityExport(Integer quantityExport) {
        this.quantityExport = quantityExport;
    }

    public Integer getQuantityActualexport() {
        return quantityActualexport;
    }

    public void setQuantityActualexport(Integer quantityActualexport) {
        this.quantityActualexport = quantityActualexport;
    }

    public IssueOrders getIdissueOrder() {
        return idissueOrder;
    }

    public void setIdissueOrder(IssueOrders idissueOrder) {
        this.idissueOrder = idissueOrder;
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
        if (!(object instanceof IssueOrderDetails)) {
            return false;
        }
        IssueOrderDetails other = (IssueOrderDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse.exam.demo.model.IssueOrderDetails[ id=" + id + " ]";
    }
    
}
