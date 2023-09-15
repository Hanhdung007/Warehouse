/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.DAL;

import java.util.Date;

/**
 *
 * @author DUNG
 */
public class issueOrderDAO {

    private Integer id;
    private Date issueDated;
    private String issueReason;
    private String submitBy;
    private Boolean issueActive;
    private String itemCode;
    private Double qtyExport;
    private Double qtyActualExport;
    private Integer itemmassterId;

    public issueOrderDAO() {
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

    public Integer getItemMasterId() {
        return itemmassterId;
    }

    public void setItemMasterId(Integer itemmassterId) {
        this.itemmassterId = itemmassterId;
    }
    
    
}
