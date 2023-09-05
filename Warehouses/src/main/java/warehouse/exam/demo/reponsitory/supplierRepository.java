/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.exam.demo.DAL.supplierDAO;
import warehouse.exam.demo.model.Supplier;

import java.util.Optional;

/**
 *
 * @author DUNG
 */
public interface supplierRepository extends JpaRepository<Supplier, String> {
//    @Query("SELECT s FROM Supplier s WHERE s.supName = :supName")
    Supplier findBySupName(String supName);
}
