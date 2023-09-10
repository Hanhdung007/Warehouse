/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import warehouse.exam.demo.DAL.warehouseDAO;
import warehouse.exam.demo.model.Warehouses;
import warehouse.exam.demo.reponsitory.warehouseRepository;
import warehouse.exam.demo.service.warehouseService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    warehouseService service;
    @Autowired
     warehouseRepository whReponsitory;
//        private String url = "http://localhost:9999/api/warehouse/";
//        private RestTemplate rest = new RestTemplate();

    @GetMapping("/index")
    public String page(Model model) {
        model.addAttribute("list", service.getAll());
        return "warehouse/index";
    }
    
     @GetMapping("/details/{id}")
    public String details(Model model,@PathVariable("id") String id) {
        model.addAttribute("model", service.findbycode(id));
        return "warehouse/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("warehouse", new Warehouses());
        return "warehouse/create";
    }
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute warehouseDAO warehouse) {
        service.saveWarehouse(warehouse);
        return "redirect:/warehouse/index";
    }
    @GetMapping("/update/{code}")
    public String update(Model model,@PathVariable("code") String code) {
        Warehouses warehouse = whReponsitory.findByCode(code);
        model.addAttribute("warehouse", warehouse);
        return "warehouse/edit";
    }

    @PostMapping("/update")
    public String update(Model model,@ModelAttribute warehouseDAO warehouses) {
         warehouseDAO warehouse = service.findbycode(warehouses.getCode());
        service.saveWarehouse(warehouses);
        return "redirect:warehouse/index";
    }
}
