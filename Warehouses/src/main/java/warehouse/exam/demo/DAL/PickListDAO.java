/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.exam.demo.DAL;

/**
 *
 * @author DUNG
 */
public class PickListDAO {

    private int itemMasterId;
    private String orderCode;

    public String orderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    private Double qty;
    private String locationCode;

    /**
     * @return the itemMasterId
     */
    public int getItemMasterId() {
        return itemMasterId;
    }

    /**
     * @return the locationCode
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * @return the qty
     */
    public Double getQty() {
        return qty;
    }

    /**
     * @param itemMasterId the itemMasterId to set
     */
    public void setItemMasterId(int itemMasterId) {
        this.itemMasterId = itemMasterId;
    }

    /**
     * @param locationCode the locationCode to set
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(Double qty) {
        this.qty = qty;
    }

}
