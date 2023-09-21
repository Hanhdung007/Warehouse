/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import static java.lang.Boolean.FALSE;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import warehouse.exam.demo.model.Log;
import warehouse.exam.demo.model.Orders;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.OrdersRepository;
import warehouse.exam.demo.reponsitory.locationReponsitory;
import warehouse.exam.demo.reponsitory.logRepository;
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
    @Autowired
    logRepository logRepository;

    @GetMapping("/")
    @PreAuthorize("hasRole('whManager')")
    public String index(Model model) {
        model.addAttribute("list", issueService.getAll());

        model.addAttribute("order", ordersRepository.findAll());
        return "issue/issueList";
    }

    @GetMapping("/confirmIssues/{id}")
    @PreAuthorize("hasRole('whManager')")
    public ResponseEntity confirmIssues(@PathVariable("id") int id) {
        IssueOrders issueOrder = issueService.findOne(id);
        Itemmasters item = itemmasterReponsitory.findById(issueOrder.getItemmasterId().getId()).get();
        Locations location = locReponsitory.findByCode(item.getLocationCode());

        issueOrder.setIssueActive(true);
        if (issueOrder.getQtyExport() > item.getQcAcceptQuantity()) {
            return ResponseEntity.ok(300);
        }
        Orders orders = ordersRepository.findByOrderCode(issueOrder.getOrderCode());
        orders.setStatus("Complete");//save
        orders.setShippedQty(issueOrder.getQtyExport());
        issueOrder.setQtyActualExport(issueOrder.getQtyExport());
        issueOrder.setAmout(orders.getAmount());
        item.setQcAcceptQuantity(item.getQcAcceptQuantity() - issueOrder.getQtyExport());
        location.setRemain(location.getRemain() + issueOrder.getQtyActualExport());
        Log log = new Log();
        log.setItemmasterId(item);
        log.setLocationName(location.getName());
        log.setMethod("Issued");
        log.setQuantity(issueOrder.getQtyExport());
        LocalDateTime ldt = LocalDateTime.now();
        Instant instant = ldt.toInstant(ZoneOffset.UTC);
        Date date = Date.from(instant);
        log.setSaveDate(date);
        logRepository.save(log);
        issueService.saveIssue(issueOrder);
        itemmasterReponsitory.save(item);
        locReponsitory.save(location);
        ordersRepository.save(orders);
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
        issueOrder.setOrderCode(pickList.orderCode());
        issueOrder.setAmout(order.getAmount());
        ordersRepository.save(order);
        issueService.saveIssue(issueOrder);
        return ResponseEntity.ok(200);
    }

}
