/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.model.Orders;
import warehouse.exam.demo.reponsitory.OrdersRepository;
import warehouse.exam.demo.service.ItemmasterService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    OrdersRepository OrderReponsitory;
    @Autowired
    ItemmasterService ItemmasterService;

    @RequestMapping("")
    @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
    public String index(Model model) {
        //model.addAttribute("attribute", "value");
        return "inventory/index";
    }

    @GetMapping("/checkStock/{code}")
    @PreAuthorize("hasRole('sale')")
    public String checkStock(Model model, @PathVariable(value = "code") String code) {
        Orders order = OrderReponsitory.findByOrderCode(code);
        model.addAttribute("order", order);
        model.addAttribute("Itemmaster", ItemmasterService.checkStock(order.getItemCode().getName()));
        return "inventory/checkStock";
    }
}
