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
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findBySupId", query = "SELECT s FROM Supplier s WHERE s.supId = :supId"),
    @NamedQuery(name = "Supplier.findBySupName", query = "SELECT s FROM Supplier s WHERE s.supName = :supName"),
    @NamedQuery(name = "Supplier.findBySupAddress", query = "SELECT s FROM Supplier s WHERE s.supAddress = :supAddress"),
    @NamedQuery(name = "Supplier.findBySupEmail", query = "SELECT s FROM Supplier s WHERE s.supEmail = :supEmail"),
    @NamedQuery(name = "Supplier.findByCity", query = "SELECT s FROM Supplier s WHERE s.city = :city"),
    @NamedQuery(name = "Supplier.findByTaxCode", query = "SELECT s FROM Supplier s WHERE s.taxCode = :taxCode"),
    @NamedQuery(name = "Supplier.findByActive", query = "SELECT s FROM Supplier s WHERE s.active = :active")})
public class Supplier implements Serializable {

    @Size(max = 2147483647)
    @Column(name = "sup_address")
    private String supAddress;
    @Size(max = 2147483647)
    @Column(name = "sup_email")
    private String supEmail;
    @Size(max = 50)
    @Column(name = "city")
    private String city;
    @Size(max = 2147483647)
    @Column(name = "tax_code")
    private String taxCode;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sup_id")
    private String supId;
//    @Size(max = 2147483647)
    @Column(name = "sup_name")
    private String supName;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "supId")
    private List<Itemmasters> itemmastersList;
    @OneToMany(mappedBy = "supId")
    private List<Importorders> importordersList;

    public Supplier() {
    }

    public Supplier(String supId) {
        this.supId = supId;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupAddress() {
        return supAddress;
    }

    public void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }

    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
    }


    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
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

    @XmlTransient
    public List<Importorders> getImportordersList() {
        return importordersList;
    }

    public void setImportordersList(List<Importorders> importordersList) {
        this.importordersList = importordersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supId != null ? supId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.supId == null && other.supId != null) || (this.supId != null && !this.supId.equals(other.supId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse.exam.demo.model.Supplier[ supId=" + supId + " ]";
    }

    
    
}
