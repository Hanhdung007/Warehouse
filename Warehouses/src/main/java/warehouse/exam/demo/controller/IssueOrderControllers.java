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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.DAL.PickListDAO;
import warehouse.exam.demo.model.IssueOrders;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.model.Orders;
import warehouse.exam.demo.service.IssueService;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.OrdersService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/issues")
public class IssueOrderControllers {

    @Autowired
    ItemmasterService itemmasterService;
    @Autowired
    OrdersService OrderService;
    @Autowired
    IssueService issueService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", issueService.getAll());
        return "issue/issueList";
    }

    @PostMapping(value = "/confirmPickList")
    public ResponseEntity confirmPickList(@RequestBody PickListDAO pickList) {
        //1. Lấy ItemMaster dựa trên itemMasterId Pick list
        //2. Check qcAcceptQty < pickList.getQty   return ResponseEntity.ok(300)
        //3. Lấy ra Order dựa trên OrderCode của Pick List
        //4. Set order.booked_qty += pickList.getQty

        //5 Tạo issue order
        /*
        issue_dated: Ngày hôm nay
        issue_reason : "Issue for order"+ OrderCode của Pick List
        submitBy: tên đăng nhập
        issue_active:false
        item_code : itemMaster.codeItemData.code ....
        qtyExport : pickList.getQty
        [qtyActualExport] : 0
        itemMaster : ItemMaster bước 1 
         */
        //6. Save lại các thay đổi
        Itemmasters item = itemmasterService.findOne(pickList.getItemMasterId());
        Orders order = OrderService.getOrderByOrderCode(pickList.orderCode());
        if (item.getQcAcceptQuantity() < pickList.getQty()) {
            return ResponseEntity.ok(300);
        }
        order.setBookQty(pickList.getQty() + order.getBookQty());
        IssueOrders issueOrder = new IssueOrders();
        issueOrder.setIssueDated(Calendar.getInstance().getTime());
        issueOrder.setIssueReason("Issue for order " + pickList.orderCode());
        issueOrder.setIssueActive(false);
        issueOrder.setItemCode(item.getCodeItemdata().getCode());
        issueOrder.setQtyExport(pickList.getQty());
        issueOrder.setQtyActualExport(0.0);
        issueOrder.setItemmasterId(item);
        OrderService.saveOrder(order);
        issueService.saveIssue(issueOrder);
        return ResponseEntity.ok(200);
    }

}
