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
    @NamedQuery(name = "Supplier.findBySupCode", query = "SELECT s FROM Supplier s WHERE s.supCode = :supCode"),
    @NamedQuery(name = "Supplier.findBySupName", query = "SELECT s FROM Supplier s WHERE s.supName = :supName"),
    @NamedQuery(name = "Supplier.findBySupAddress", query = "SELECT s FROM Supplier s WHERE s.supAddress = :supAddress"),
    @NamedQuery(name = "Supplier.findBySupEmail", query = "SELECT s FROM Supplier s WHERE s.supEmail = :supEmail"),
    @NamedQuery(name = "Supplier.findByCity", query = "SELECT s FROM Supplier s WHERE s.city = :city"),
    @NamedQuery(name = "Supplier.findByTaxCode", query = "SELECT s FROM Supplier s WHERE s.taxCode = :taxCode"),
    @NamedQuery(name = "Supplier.findByBit", query = "SELECT s FROM Supplier s WHERE s.bit = :bit")})
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sup_code")
    private String supCode;
    @Size(max = 2147483647)
    @Column(name = "sup_name")
    private String supName;
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
    @Column(name = "bit")
    private Boolean bit;
    @OneToMany(mappedBy = "supCode")
    private List<Itemmasters> itemmastersList;

    public Supplier() {
    }

    public Supplier(String supCode) {
        this.supCode = supCode;
    }

    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Boolean getBit() {
        return bit;
    }

    public void setBit(Boolean bit) {
        this.bit = bit;
    }

    @XmlTransient
    public List<Itemmasters> getItemmastersList() {
        return itemmastersList;
    }

    public void setItemmastersList(List<Itemmasters> itemmastersList) {
        this.itemmastersList = itemmastersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supCode != null ? supCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.supCode == null && other.supCode != null) || (this.supCode != null && !this.supCode.equals(other.supCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse.exam.demo.model.Supplier[ supCode=" + supCode + " ]";
    }
    
}
