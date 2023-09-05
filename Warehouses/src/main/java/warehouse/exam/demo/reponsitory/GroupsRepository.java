/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import org.springframework.data.repository.CrudRepository;
import warehouse.exam.demo.model.Groups;

/**
 *
 * @author LAPTOP123
 */
public interface GroupsRepository extends CrudRepository<Groups, Integer> {
    
}
