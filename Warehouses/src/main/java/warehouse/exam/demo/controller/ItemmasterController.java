/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.service.IetmmasterService;

import java.text.ParseException;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/itemaster")
public class ItemmasterController {
    @Autowired
    IetmmasterService service;
    @GetMapping("/index")
    public String index(Model model) throws ParseException {
        model.addAttribute("list", service.getAll());
        return "itemaster/index";
    }

//    @GetMapping("/create/{id}")
//    public String create(@PathVariable int id, Model model){
//        model.addAttribute("idImport", id);
//        model.addAttribute("item", new itemmasterDAO());
//        return "import/createItem";
//    }
//
//    @PostMapping("/create")
//    public String create(@ModelAttribute itemmasterDAO item, Model model){
//        service.saveItemMaster(item, item.getIdImport());
//        return "redirect:/import/index";
//    }
}
