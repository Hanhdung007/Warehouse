/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.service.itemdataService;

/**
 *
 * @author DUNG
 */
@Controller
 @RequestMapping("/itemdata")
public class itemDataController {
    @Autowired
    itemdataService itemService;
    
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", itemService.getAll());
        return "itemdata/index";
    }
    
}
