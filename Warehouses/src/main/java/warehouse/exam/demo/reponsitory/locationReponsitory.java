/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import warehouse.exam.demo.model.Locations;

/**
 *
 * @author DUNG
 */
public interface locationReponsitory extends JpaRepository<Locations, String> {

    Locations findByCode(String code);

    Locations findByName(String name);

    @Query("SELECT l FROM Locations l WHERE l.remain > 0")
    List<Locations> getRemainLocation();

    @Query("SELECT c FROM Locations c WHERE c.warehouseCode.code = :warehouseCode")
    List<Locations> getLocationByWarehouseCode(String warehouseCode);
}
