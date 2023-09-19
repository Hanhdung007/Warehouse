/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import warehouse.exam.demo.model.Orders;

/**
 *
 * @author LAPTOP123
 */
public interface OrdersRepository extends JpaRepository<Orders, String> {
    @Query("Select o from Orders o where o.orderCode = :orderCode")
    Orders findByOrderCode(String orderCode);
}
