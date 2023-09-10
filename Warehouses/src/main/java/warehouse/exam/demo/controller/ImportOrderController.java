/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.DAL.supplierDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.ImportService;
import warehouse.exam.demo.service.itemdataService;
import warehouse.exam.demo.service.locationService;
import warehouse.exam.demo.service.supplierService;
import warehouse.exam.demo.model.Supplier;
import warehouse.exam.demo.service.*;

import javax.validation.Valid;
import java.util.List;

/**
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
        List<importDAO> searchList = (List<importDAO>) model.asMap().get("searchResults");
        if (searchList != null) {
            model.addAttribute("list", searchList);
        } else {
            model.addAttribute("list", service.getAll());
        }
        return "import/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, RedirectAttributes redirectAttributes) {
        List<importDAO> foundOrders = service.searchImports(keyword);
        redirectAttributes.addFlashAttribute("searchResults", foundOrders);
        return "redirect:/import/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("import", new importDAO());
        model.addAttribute("supplier", supService.getAll());
        return "import/create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute importDAO imp) {
        model.addAttribute("supplier", supService.getAll());
        imp.setStatus(false);
        service.saveImportOrder(imp);
        return "redirect:/import/index";
    }

    @GetMapping("details/{id}")
    public String details(Model model, @PathVariable("id") int id) {
        model.addAttribute("import", service.findOne(id));
        return "import/detail";
    }

    @GetMapping("edit/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("import", service.findOne(id));
        model.addAttribute("supplier", supService.getAll());
        return "import/edit";
    }

    @PostMapping("/edit")
    public String update(importDAO imp, BindingResult binding){
        if(binding.hasErrors()){
            return "/edit";
        }
        else {
            service.updateImpOrder(imp);
            return "redirect:/import/index";
        }
    }

    @GetMapping("/createItem/{id}")
    public String createItem(Model model, @PathVariable("id") int id) {
        itemmasterDAO itemDAO = new itemmasterDAO();
//        Importorders idImport = service.findOne(id);
//        itemDAO.setIdImport(importorders);
        model.addAttribute("itemmasterDAO", itemDAO);
        model.addAttribute("idImport", service.findOne(id));
        model.addAttribute("supplier", supService.getAll());
        model.addAttribute("itemdata", itemdataService.getAll());
        return "import/createItem";
    }

    @PostMapping("/createItem")
    public String createItemMaster(Model model, @RequestParam("idImp") int idImp, @ModelAttribute itemmasterDAO itemMaster, @ModelAttribute Itemmasters item) {
        //model.addAttribute("idImport", itemmasterService.findOne(idImp));
        Itemmasters newItem =itemmasterService.saveItemMaster(itemMaster, idImp, item);
        return "redirect:/import/index";
    }
}
