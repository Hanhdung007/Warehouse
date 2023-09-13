/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.DAL;

import warehouse.exam.demo.model.Itemmasters;

/**
 * @author DUNG
 */
public class itemmasterDAO {
    public itemmasterDAO(Itemmasters item) {
        this.id = item.getId();
        this.quantity = item.getQuantity();
        this.recieveNo = item.getRecieveNo();
        this.dateImport = item.getDateImport();
        this.note = item.getNote();
        this.qcAcceptQuantity = item.getQcAcceptQuantity();
        this.qcInjectQuantity = item.getQcInjectQuantity();
        this.qcBy = item.getQcBy();
        this.codeItemdata = item.getCodeItemdata().getName();
    }

    public itemmasterDAO() {
    }

    private Integer id;
    private String locationName;
    private Double quantity;
    private String recieveNo;
    private String dateImport;
    private String note;
    private Double qcAcceptQuantity;
    private Double qcInjectQuantity;
    private String qcBy;
    private int idImport;
    private String itemName;
    private String codeItemdata;
    private String supplierName;
    //    private Itemdatas codeItemdatas;
    private String image;
    private Boolean disable;
    private Boolean pass;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
//    public String getLocationName() {
//        return locationName;
//    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    public Double getQcInjectQuantity() {
        return qcInjectQuantity;
    }
    public void setQcInjectQuantity(Double qcInjectQuantity) {
        this.qcInjectQuantity = qcInjectQuantity;
    }

    public String getQcBy() {
        return qcBy;
    }

    public void setQcBy(String qcBy) {
        this.qcBy = qcBy;
    }

    public int getIdImport() {
        return idImport;
    }

//    public Itemdatas getCodeItemdatas() {
//        return codeItemdatas;
//    }
//
//    public void setCodeItemdatas(Itemdatas codeItemdatas) {
//        this.codeItemdatas = codeItemdatas;
//    }

    public void setIdImport(int idImport) {
        this.idImport = idImport;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCodeItemdata() {
        return codeItemdata;
    }

    public void setCodeItemdata(String codeItemdata) {
        this.codeItemdata = codeItemdata;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Boolean getPass() {
        return pass;
    }

    public Boolean setPass(Boolean pass) {
        this.pass = pass;
        return pass;
    }
}
