/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package warehouse.exam.demo.reponsitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import warehouse.exam.demo.model.IssueOrders;

/**
 *
 * @author DUNG
 */
public interface IssueRepository extends JpaRepository<IssueOrders, Integer> {

    IssueOrders findById(int id);
    @Query("SELECT o From IssueOrders o where o.issueActive = false")
    List<IssueOrders> GetIssueList();
}
