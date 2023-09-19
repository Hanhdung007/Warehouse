/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import static java.lang.Boolean.FALSE;
import java.util.Calendar;
import java.util.Optional;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.DAL.PickListDAO;
import warehouse.exam.demo.model.IssueOrders;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.model.Locations;
import warehouse.exam.demo.model.Orders;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.OrdersRepository;
import warehouse.exam.demo.reponsitory.locationReponsitory;
import warehouse.exam.demo.service.IssueService;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.OrdersService;
import warehouse.exam.demo.service.locationService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/issues")
public class IssueOrderControllers {

    @Autowired
    ItemmasterRepository itemmasterReponsitory;
    @Autowired
    IssueService issueService;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    locationReponsitory locReponsitory;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", issueService.getAll());
        return "issue/issueList";
    }

    @GetMapping("/confirmIssues/{id}")
    public ResponseEntity confirmIssues(@PathVariable("id") int id) {
        //1 Lấy isssue order dựa trên id 
        //2. Nếu QtyActualExport = qtyExport => active = true
        //3. Lấy item master dựa trên issue order.itemmaster_id
        //4. Trừ qc_accept_quantity 1 lượng = qtyExport
        //5 Lấy location từ itemmaster.locCode
        //6. + remain 1 lượng = QtyActualExport
        //7. Update các thay đổi
        IssueOrders issueOrder = issueService.findOne(id);
        Itemmasters item = itemmasterReponsitory.findById(issueOrder.getItemmasterId().getId()).get();
        Locations location = locReponsitory.findByCode(item.getLocationCode());
        
            issueOrder.setIssueActive(true);
        if (issueOrder.getQtyExport() > item.getQcAcceptQuantity()) {
            return ResponseEntity.ok(300);
        }
        item.setQcAcceptQuantity(item.getQcAcceptQuantity() - issueOrder.getQtyExport());
        location.setRemain(location.getRemain() + issueOrder.getQtyActualExport());
        issueService.saveIssue(issueOrder);
        itemmasterReponsitory.save(item);
        locReponsitory.save(location);
        return ResponseEntity.ok(200);
    }

    @PostMapping(value = "/confirmPickList")
    public ResponseEntity confirmPickList(@RequestBody PickListDAO pickList) {
        Itemmasters item = itemmasterReponsitory.findById(pickList.getItemMasterId()).get();
        Orders order = ordersRepository.findByOrderCode(pickList.orderCode());
        if (item.getQcAcceptQuantity() < pickList.getQty()) {
            return ResponseEntity.ok(300);
        }
        order.setBookQty(pickList.getQty() + order.getBookQty());
        IssueOrders issueOrder = new IssueOrders();
        issueOrder.setIssueDated(Calendar.getInstance().getTime());
        issueOrder.setIssueReason("Issue for order : " + pickList.orderCode());
        issueOrder.setIssueActive(false);
        issueOrder.setItemCode(item.getCodeItemdata().getCode());
        issueOrder.setQtyExport(pickList.getQty());
        issueOrder.setQtyActualExport(0.0);
        issueOrder.setItemmasterId(item);
        ordersRepository.save(order);
        issueService.saveIssue(issueOrder);
        return ResponseEntity.ok(200);
    }

}
