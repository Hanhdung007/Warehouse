/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import warehouse.exam.demo.reponsitory.supplierRepository;
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
    @Autowired
    supplierRepository supRepo;

    @RequestMapping("/index")
    @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
    public String index(Model model) {
        model.addAttribute("list", service.getAll());
        return "supplier/supIndex";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('sale','admin')")
    public String create(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier/createSup";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('sale','admin')")
    public String create(Model model, @ModelAttribute supplierDAO dao) {
        dao.setActive(true);
        service.saveSupplier(dao);
        return "redirect:/supplier/index";
    }

    @GetMapping("/update/{code}")
    @PreAuthorize("hasAnyRole('sale','admin')")
    public String findOne(Model model, @PathVariable("code") String code) {
        Supplier sup = service.findbycode(code);
        model.addAttribute("supplier", sup);
        return "supplier/editSup";
    }
    
    @GetMapping("/updateStatus/{code}")
    @PreAuthorize("hasAnyRole('sale','admin')")
    public ResponseEntity updateStatus(@PathVariable("code") String code){
        Supplier sup = service.findbycode(code);
        sup.setActive(!sup.getActive());
        supRepo.save(sup);
        return ResponseEntity.ok(200);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('sale','admin')")
    public String update(Model model, @ModelAttribute supplierDAO dao) {
        service.saveSupplier(dao);
        return "redirect:/supplier/index";
    }

    @PostMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('sale','admin')")
    public String updateStatus(Model model, @ModelAttribute supplierDAO dao) {
        service.updateStatusSupplier(dao);
        return "redirect:/supplier/index";
    }
}
