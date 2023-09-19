/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import warehouse.exam.demo.model.AllocateOrder;
import warehouse.exam.demo.model.Log;

/**
 *
 * @author DUNG
 */
public interface allocateRepository extends JpaRepository<AllocateOrder, Integer> {

    @Query("SELECT a FROM AllocateOrder a WHERE a.locationCode != :locationCode AND a.itemMasterId = :itemMasterId AND a.id != :id and a.confirm = true")
     List<AllocateOrder> finbyItemAndLocation(String locationCode, int itemMasterId, int id);

    @Query("SELECT a FROM AllocateOrder a WHERE a.confirm = false")
    List<AllocateOrder> GetAllocateOrderbyFalse();
    AllocateOrder findById(int id);
}

