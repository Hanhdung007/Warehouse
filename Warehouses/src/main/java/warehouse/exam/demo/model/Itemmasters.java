/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DUNG
 */
@Entity
@Table(name = "itemmasters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemmasters.findAll", query = "SELECT i FROM Itemmasters i"),
    @NamedQuery(name = "Itemmasters.findById", query = "SELECT i FROM Itemmasters i WHERE i.id = :id"),
    @NamedQuery(name = "Itemmasters.findByLocationCode", query = "SELECT i FROM Itemmasters i WHERE i.locationCode = :locationCode"),
    @NamedQuery(name = "Itemmasters.findByQuantity", query = "SELECT i FROM Itemmasters i WHERE i.quantity = :quantity"),
    @NamedQuery(name = "Itemmasters.findByRecieveNo", query = "SELECT i FROM Itemmasters i WHERE i.recieveNo = :recieveNo"),
    @NamedQuery(name = "Itemmasters.findByDateImport", query = "SELECT i FROM Itemmasters i WHERE i.dateImport = :dateImport"),
    @NamedQuery(name = "Itemmasters.findByNote", query = "SELECT i FROM Itemmasters i WHERE i.note = :note"),
    @NamedQuery(name = "Itemmasters.findByQcAcceptQuantity", query = "SELECT i FROM Itemmasters i WHERE i.qcAcceptQuantity = :qcAcceptQuantity"),
    @NamedQuery(name = "Itemmasters.findByQcBy", query = "SELECT i FROM Itemmasters i WHERE i.qcBy = :qcBy")})
public class Itemmasters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "location_code")
    private String locationCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity")
    private Double quantity;
    @Size(max = 255)
    @Column(name = "recieve_no")
    private String recieveNo;
    @Column(name = "date_import")
//    @Temporal(TemporalType.TIMESTAMP)
    private String dateImport;
    @Size(max = 255)
    @Column(name = "note")
    private String note;
    @Column(name = "qc_accept_quantity")
    private Double qcAcceptQuantity;
    @Column(name = "book_qty")
    private Double bookQty;
    @Size(max = 255)
    @Column(name = "qc_by")
    private String qcBy;
    @JoinColumn(name = "id_import", referencedColumnName = "id")
    @ManyToOne
    private Importorders idImport;
    @JoinColumn(name = "code_itemdata", referencedColumnName = "code")
    @ManyToOne
    private Itemdatas codeItemdata;
    @JoinColumn(name = "sup_id", referencedColumnName = "sup_id")
    @ManyToOne
    private Supplier supId;

    public Itemmasters() {
    }

    public Itemmasters(Integer id) {
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getBookQty() {
        return bookQty;
    }

    public void setBookQty(Double bookQty) {
        this.bookQty = bookQty;
    }

    public String getRecieveNo() {
        return recieveNo;
    }

    public void setRecieveNo(String recieveNo) {
        this.recieveNo = recieveNo;
    }

    public String getDateImport() {
        return dateImport;
    }
    public void setDateImport(String dateImport) {
        this.dateImport = dateImport;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getQcAcceptQuantity() {
        return qcAcceptQuantity;
    }

    public void setQcAcceptQuantity(Double qcAcceptQuantity) {
        this.qcAcceptQuantity = qcAcceptQuantity;
    }

    public String getQcBy() {
        return qcBy;
    }

    public void setQcBy(String qcBy) {
        this.qcBy = qcBy;
    }

    public Importorders getIdImport() {
        return idImport;
    }

    public void setIdImport(Importorders idImport) {
        this.idImport = idImport;
    }

    public Itemdatas getCodeItemdata() {
        return codeItemdata;
    }

    public void setCodeItemdata(Itemdatas codeItemdata) {
        this.codeItemdata = codeItemdata;
    }

    public Supplier getSupId() {
        return supId;
    }

    public void setSupId(Supplier supId) {
        this.supId = supId;
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
        if (!(object instanceof Itemmasters)) {
            return false;
        }
        Itemmasters other = (Itemmasters) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse.exam.demo.model.Itemmasters[ id=" + id + " ]";
    }

}
