/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.exam.demo.model.Itemdatas;

public interface itemdataReponsitory extends JpaRepository<Itemdatas, String> {
    Itemdatas findByName(String name);
    Itemdatas findByCode(String code);
}
