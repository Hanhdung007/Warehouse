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
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.service.ItemmasterService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/inventory")
public class InventoryController {
    
   
    
    @RequestMapping("")
    public String index(Model model) {
        //model.addAttribute("attribute", "value");
        return "inventory/index";
    }
    
    
}
