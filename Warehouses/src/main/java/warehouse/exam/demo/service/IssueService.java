/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.issueOrderDAO;
import warehouse.exam.demo.model.IssueOrders;
import warehouse.exam.demo.reponsitory.IssueRepository;

/**
 *
 * @author DUNG
 */
@Service
public class IssueService {
    
    @Autowired
    IssueRepository issueReponsitory;
    
    public List<issueOrderDAO> getAll() {
        List<issueOrderDAO> dao = new ArrayList<>();
        List<IssueOrders> whs = issueReponsitory.findAll();
        for (IssueOrders issue : whs) {
            issueOrderDAO issuedao = new issueOrderDAO();
            issuedao.setId(issue.getId());
            issuedao.setIssueActive(issue.getIssueActive());
            issuedao.setIssueDated(issue.getIssueDated());
            issuedao.setIssueReason(issue.getIssueReason());
            issuedao.setItemCode(issue.getItemmasterId().getCodeItemdata().getCode());
            issuedao.setItemMasterId(issue.getItemmasterId().getId());
            issuedao.setQtyActualExport(issue.getQtyActualExport());
            issuedao.setQtyExport(issue.getQtyExport());
            issuedao.setSubmitBy(issue.getSubmitBy());
            dao.add(issuedao);
        }
        return dao;
    }
    
    public IssueOrders saveIssue(IssueOrders issue) {
        return issueReponsitory.save(issue);
    }
}
