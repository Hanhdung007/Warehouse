/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.DAL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Itemdatas;
import warehouse.exam.demo.model.Locations;

/**
 *
 * @author DUNG
 */
public class itemmasterDAO {

    private Integer id;
    private String locationName;
    private Double quantity;
    private String recieveNo;

    private String dateImport;
    private String note;
    private Double qcAcceptQuantity;
    private String qcBy;
    private int idImport;
    private String itemName;
    private String supplierName;
    private String image;

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

    public String getLocationName() {
        return locationName;
    }

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

    public void setDateImport(String dateImport){
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

    public int getIdImport() {
        return idImport;
    }

    public void setIdImport(int idImport) {
        this.idImport = idImport;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
