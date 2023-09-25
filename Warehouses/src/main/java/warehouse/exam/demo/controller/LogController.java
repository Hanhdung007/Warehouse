/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/logs")
public class LogController {
    
     @RequestMapping("")
     @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
    public String index(Model model) {
        //model.addAttribute("attribute", "value");
        return "logs/index";
    }
    
}
