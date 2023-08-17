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
import warehouse.exam.demo.service.warehouseService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

//    @Autowired
//    warehouseService service;
        private String url = "http://localhost:9999/api/warehouse/";
        private RestTemplate rest = new RestTemplate();

    @GetMapping("/index")
    public String page(Model model) {
        List<Warehouses> result = rest.getForObject(url, List.class);
        model.addAttribute("list", result);
        return "warehouse/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("warehouse", new Warehouses());
        return "warehouse/create";
    }
//    @PostMapping()
//    public String create(Model model, @ModelAttribute warehouseDAO warehouse) {
//        service.saveWarehouse(warehouse);
//        return "redirect:/index";
//    }
//    @GetMapping("/update/{code}")
//    public String update(Model model,@PathVariable("code") String code) {
//        Warehouses warehouse = service.findbycode(code);
//        model.addAttribute("warehouse", warehouse);
//        return "warehouse/edit";
//    }
//
//    @PostMapping("/update")
//    public String update(Model model,@ModelAttribute warehouseDAO warehouses) {
//         Warehouses warehouse = service.findbycode(warehouses.getCode());
//        service.saveWarehouse(warehouses);
//        return "redirect:warehouse/index";
//    }
}
