/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.model.Itemmasters;

/**
 *
 * @author DUNG
 */
public interface ItemmasterRepository extends JpaRepository<Itemmasters, Integer> {
//    @Query("SELECT im, loc FROM itemmasters im JOIN  locations loc ON im.code_location = loc.code")
//    List<Object[]> getItemmasterWithLocation();

    @Query("SELECT o From Itemmasters o where o.qcBy != '' and o.locationCode = '' and o.qcAcceptQuantity > o.bookQty")
    List<Itemmasters> GetUnAllocated();

    @Query("SELECT NEW warehouse.exam.demo.DAL.itemmasterDAO(item) FROM Itemmasters item WHERE CAST(item.quantity AS string) LIKE %:keyword% OR item.codeItemdata.name LIKE %:keyword% OR item.qcBy LIKE %:keyword% OR CAST(item.qcAcceptQuantity AS string) LIKE %:keyword% OR CAST(item.qcInjectQuantity AS string) LIKE %:keyword% OR item.recieveNo LIKE %:keyword% OR item.note LIKE %:keyword%")
    List<itemmasterDAO> searchAllItem(@Param("keyword") String keyword);
    Itemmasters findByLocationCode(String locationCode);

    @Query("Select o FROM Itemmasters o where o.qcBy != '' and o.locationCode =:locationCode")
    List<Itemmasters> findItemsByLocationsCode(String locationCode);

    @Query("SELECT o FROM Itemmasters o WHERE o.qcBy != '' and o.locationCode != '' ")
    List<Itemmasters> findAllInventorys();

    @Query("SELECT o FROM Itemmasters o WHERE o.codeItemdata.code = :itemCode AND o.locationCode != '' AND o.qcAcceptQuantity > 0")
    List<Itemmasters> checkStock(String itemCode);
    @Query("Select o FROM Itemmasters o where o.locationCode ='' and o.disable = true")
    List<Itemmasters> findItemQC();
}
