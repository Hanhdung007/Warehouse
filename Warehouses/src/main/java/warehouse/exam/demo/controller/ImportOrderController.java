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
import warehouse.exam.demo.DAL.itemdataDAO;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Itemdatas;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.ImportService;
import warehouse.exam.demo.service.itemdataService;
import warehouse.exam.demo.service.locationService;
import warehouse.exam.demo.service.supplierService;

/**
 *
 * @author DUNG
 */
@RequestMapping("/import")
@Controller
public class ImportOrderController {
    
    @Autowired
    ImportService service;
    @Autowired
    supplierService supService;
    @Autowired
    locationService locService;
    @Autowired
    itemdataService itemdataService;
    @Autowired
    ItemmasterService itemmasterService;
    
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", service.getAll());
        return "import/index";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("import", new importDAO());
        model.addAttribute("supplier", supService.getAll());
        return "import/create";
    }
    
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute importDAO dao) {
        model.addAttribute("supplier", supService.getAll());
        dao.setStatus(false);
        service.saveImportOrder(dao);
        return "redirect:/import/index ";
    }
    
    @GetMapping("details/{id}")
    public String details(Model model, @PathVariable("id") int id) {
        model.addAttribute("import", service.findOne(id));
        return "import/detail";
    }
    
    @GetMapping("update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("import", service.findOne(id));
        return "import/update";
    }
    
    @GetMapping("/createItem/{id}")
    public String createItem(Model model, @PathVariable("id") int id) {
        itemmasterDAO itemDAO = new itemmasterDAO();
        Importorders importorders = service.findOne(id);
//        itemDAO.setIdImport(importorders);
        model.addAttribute("itemmasterDAO", itemDAO);
        model.addAttribute("supplier", supService.getAll());
        model.addAttribute("itemdata", itemdataService.getAll());
        return "import/createItem";
    }
    
//    @PostMapping("/createItem")
//    public String createItemMaster(Model model, @ModelAttribute itemmasterDAO itemmasterDAO) {
////        itemmasterDAO.setQcBy("");
////        itemmasterDAO.setQcAcceptQuantity(0.0);
////        itemmasterDAO.setLocationName("");
////        Importorders importorders = service.findOne(itemmasterDAO.getImportID());
////        Itemmasters itemmaster = new Itemmasters();
////        itemmasterDAO.setImportID(itemmaster.getIdImport().getId());
////        itemmasterDAO.setIdImport(importorders);
//        model.addAttribute("supplier", supService.getAll());
//        model.addAttribute("itemdata", itemdataService.getAll());
//        itemmasterService.saveItemMaster(itemmasterDAO);
//        return "redirect:/import/index";
//    }
}
