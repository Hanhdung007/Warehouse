/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.DAL.supplierDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Supplier;
import warehouse.exam.demo.service.supplierService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    supplierService service;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", service.getAll());
        return "supplier/supIndex";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier/createSup";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute supplierDAO dao) {
        dao.setActive(false);
        service.saveSupplier(dao);
        return "redirect:/supplier/index";
    }

//    @GetMapping("/{id}")
//    public String findOne(Model model, @PathVariable("id") int id) {
//        model.addAttribute("import", service.findOne(id));
//        return "import/detail";
//    }

}
