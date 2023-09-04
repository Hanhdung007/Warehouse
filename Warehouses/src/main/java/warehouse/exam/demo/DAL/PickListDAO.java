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
    private double qty;
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
    public double getQty() {
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
    public void setQty(double qty) {
        this.qty = qty;
    }

}
