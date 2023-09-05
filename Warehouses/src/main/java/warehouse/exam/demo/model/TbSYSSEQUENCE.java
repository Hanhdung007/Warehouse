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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LAPTOP123
 */
@Entity
@Table(name = "tb_SYS_SEQUENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSYSSEQUENCE.findAll", query = "SELECT t FROM TbSYSSEQUENCE t"),
    @NamedQuery(name = "TbSYSSEQUENCE.findBySeqname", query = "SELECT t FROM TbSYSSEQUENCE t WHERE t.seqname = :seqname"),
    @NamedQuery(name = "TbSYSSEQUENCE.findBySeqvalue", query = "SELECT t FROM TbSYSSEQUENCE t WHERE t.seqvalue = :seqvalue")})
public class TbSYSSEQUENCE implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SEQNAME")
    private String seqname;
    @Column(name = "SEQVALUE")
    private Integer seqvalue;

    public TbSYSSEQUENCE() {
    }

    public TbSYSSEQUENCE(String seqname) {
        this.seqname = seqname;
    }

    public String getSeqname() {
        return seqname;
    }

    public void setSeqname(String seqname) {
        this.seqname = seqname;
    }

    public Integer getSeqvalue() {
        return seqvalue;
    }

    public void setSeqvalue(Integer seqvalue) {
        this.seqvalue = seqvalue;
    }
}
