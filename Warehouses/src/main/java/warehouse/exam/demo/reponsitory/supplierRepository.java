/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import warehouse.exam.demo.model.Supplier;

/**
 *
 * @author DUNG
 */
public interface supplierRepository extends JpaRepository<Supplier, String> {
//    @Query("SELECT s FROM Supplier s WHERE s.supName = :supName")
    Supplier findBySupName(String supName);
}
