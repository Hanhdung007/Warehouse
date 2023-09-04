/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    Itemmasters findByLocationCode(String locationCode);
}
