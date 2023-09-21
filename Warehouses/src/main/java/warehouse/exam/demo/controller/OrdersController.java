/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import warehouse.exam.demo.model.Customers;
import warehouse.exam.demo.model.Groups;
import warehouse.exam.demo.model.Itemdatas;
import warehouse.exam.demo.model.Orders;
import warehouse.exam.demo.model.Unit;
import warehouse.exam.demo.service.OrdersService;

/**
 *
 * @author LAPTOP123
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final String url = "http://localhost:9999/api/orders/";
    private final RestTemplate rest;
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(RestTemplate restTemplate, OrdersService ordersService) {
        this.rest = restTemplate;
        this.ordersService = ordersService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('sale', 'importer')")
    public String listOrders(Model model) {
        Orders[] ordersArray = rest.getForObject(url, Orders[].class);
        model.addAttribute("orders", ordersArray);
        return "orders/list";
    }

    @GetMapping("/new-order")
    @PreAuthorize("hasAnyRole('sale', 'importer')")
    public String showAddOrderForm(Model model) {
        Orders newOrder = new Orders();

        Customers[] customersArray = rest.getForObject("http://localhost:9999/api/customers/", Customers[].class);
        Groups[] groupsArray = rest.getForObject("http://localhost:9999/api/groups/", Groups[].class);
        Unit[] unitsArray = rest.getForObject("http://localhost:9999/api/units/", Unit[].class);
        Itemdatas[] itemdataArray = rest.getForObject("http://localhost:9999/api/itemdata/", Itemdatas[].class);
        model.addAttribute("order", newOrder);
        model.addAttribute("customers", customersArray);
        model.addAttribute("groups", groupsArray);
        model.addAttribute("units", unitsArray);
        model.addAttribute("itemdata", itemdataArray);
        return "orders/new-order";
    }

    @PostMapping("/new-order")
    @PreAuthorize("hasAnyRole('sale', 'importer')")
    public String saveOrder(@ModelAttribute("order") Orders order) {
        Orders savedOrder = ordersService.saveOrder(order);
        return "redirect:/orders/list";
    }

    @GetMapping("/edit-order/{orderCode}")
    @PreAuthorize("hasAnyRole('sale', 'importer')")
    public String showEditOrderForm(@PathVariable String orderCode, Model model) {
        Orders orderToEdit = rest.getForObject(url + orderCode, Orders.class);

        // Lấy danh sách khách hàng, nhóm và đơn vị từ API hoặc dịch vụ
        Customers[] customersArray = rest.getForObject("http://localhost:9999/api/customers/", Customers[].class);
        Groups[] groupsArray = rest.getForObject("http://localhost:9999/api/groups/", Groups[].class);
        Unit[] unitsArray = rest.getForObject("http://localhost:9999/api/units/", Unit[].class);
        Itemdatas[] itemdataArray = rest.getForObject("http://localhost:9999/api/itemdata/", Itemdatas[].class);
        
        model.addAttribute("order", orderToEdit);
        model.addAttribute("customers", customersArray);
        model.addAttribute("groups", groupsArray);
        model.addAttribute("units", unitsArray);
        model.addAttribute("itemdata", itemdataArray);

        return "orders/edit-order";
    }

    @PostMapping("/edit-order")
    @PreAuthorize("hasAnyRole('sale', 'importer')")
    public String updateOrder(@ModelAttribute("order") Orders updatedOrder) {
        Orders savedOrder = ordersService.updateOrder(updatedOrder);
        return "redirect:/orders/list";
    }
}
