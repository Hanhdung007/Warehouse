/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.model.Importorders;

import java.util.List;

public interface ImportRepository extends JpaRepository<Importorders, Integer> {
    Importorders findById(int id);

//    @Query("SELECT o FROM Importorders o WHERE o.driver LIKE %:keyword% OR o.driversPhone LIKE %:keyword%")
//    List<importDAO> searchAllImport(String keyword);

    @Query("SELECT NEW warehouse.exam.demo.DAL.importDAO(import) FROM Importorders import WHERE import.driver LIKE %:keyword% OR import.driversPhone LIKE %:keyword% OR import.supId.supName LIKE %:keyword% OR import.note LIKE %:keyword%")
    List<importDAO> searchAllImport(@Param("keyword") String keyword);

//    OR o.supId.supName LIKE %:keyword% OR CAST(o.dateImport AS string) LIKE %:keyword% OR o.note LIKE %:keyword% OR CAST(o.status AS string) LIKE %:keyword%
}