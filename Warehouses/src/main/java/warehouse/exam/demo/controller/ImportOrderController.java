/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.ImportService;
import warehouse.exam.demo.service.itemdataService;
import warehouse.exam.demo.service.locationService;
import warehouse.exam.demo.service.supplierService;
import java.util.List;
import warehouse.exam.demo.reponsitory.ImportRepository;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.itemdataReponsitory;
import warehouse.exam.demo.reponsitory.supplierRepository;

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

    @Autowired
    ItemmasterRepository imMasterRepositoty;
    @Autowired
    itemdataReponsitory imDataRepository;
    @Autowired
    supplierRepository supReponsitory;
     @Autowired
    ImportRepository impRepository;

    @RequestMapping("/index")
    @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
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
    @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
    public String search(@RequestParam("keyword") String keyword, RedirectAttributes redirectAttributes) {
        List<importDAO> foundOrders = service.searchImports(keyword);
        redirectAttributes.addFlashAttribute("searchResults", foundOrders);
        return "redirect:/import/index";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('admin','importer')")
    public String create(Model model) {
        model.addAttribute("import", new importDAO());
        model.addAttribute("supplier", supService.getAll());
        return "import/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('admin','importer')")
    public String create(Model model, @ModelAttribute importDAO imp) {
        model.addAttribute("supplier", supService.getAll());
        imp.setStatus(false);
        service.saveImportOrder(imp);
        return "redirect:/import/index";
    }

    @GetMapping("details/{id}")
    @PreAuthorize("hasAnyRole('admin','importer')")
    public String details(Model model, @PathVariable("id") int id) {
        model.addAttribute("import", service.findOne(id));
        return "import/detail";
    }

    @PostMapping("/details/{idImport}")
    @PreAuthorize("hasAnyRole('admin','importer')")
    public String updateDisable(@PathVariable int idImport, @RequestParam int id){
        Boolean newDisable = true;
        itemmasterService.updateDisable(id, newDisable);
        return "redirect:/import/details/" + idImport;
    }

    @GetMapping("edit/{id}")
    @PreAuthorize("hasAnyRole('admin','importer')")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("import", service.findOne(id));
        model.addAttribute("supplier", supService.getAll());
        return "import/edit";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyRole('admin','importer')")
    public String update(importDAO imp, BindingResult binding) {
        if (binding.hasErrors()) {
            return "/error/error404";
        } else {
            service.updateImpOrder(imp);
            return "redirect:/import/index";
        }
    }

    @GetMapping("/createItem/{id}")
    @PreAuthorize("hasAnyRole('admin','importer')")
    public String createItem(Model model, @PathVariable("id") int id) {
        itemmasterDAO itemDAO = new itemmasterDAO();
        model.addAttribute("itemmasterDAO", itemDAO);
        model.addAttribute("idImport", service.findOne(id));
        model.addAttribute("supplier", supService.getAll());
        model.addAttribute("itemdata", itemdataService.getAll());
        return "import/createItem";
    }


    @RequestMapping(value="/createItemMaster", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('admin','importer')")
    public String createItemMaster(Model model, @RequestParam("idImp") int idImp, @ModelAttribute itemmasterDAO itemmasterDAO) {
        Importorders imp = impRepository.findById(idImp);
        Itemmasters item = new Itemmasters();
//model.addAttribute("idImport", itemmasterService.findOne(idImp));
        item.setId(itemmasterDAO.getId());
        item.setCodeItemdata(imDataRepository.findByName(itemmasterDAO.getItemName()));
        item.setDateImport(itemmasterDAO.getDateImport());
        item.setIdImport(imp);
//          item.setLocationCode(itemmasterDAO.);
        item.setNote(itemmasterDAO.getNote());
        item.setQcAcceptQuantity(itemmasterDAO.getQcAcceptQuantity());
        item.setQcBy(itemmasterDAO.getQcBy());
        item.setRecieveNo(itemmasterDAO.getRecieveNo());
        item.setSupId(supReponsitory.findBySupName(itemmasterDAO.getSupplierName()));
        item.setQuantity(itemmasterDAO.getQuantity());
        item.setPass(false);
        item.setDisable(false);
        item.setQcAcceptQuantity(0.0);
        item.setQcInjectQuantity(0.0);
        item.setLocationCode("");
        imMasterRepositoty.save(item) ;
        return "redirect:/import/index";
    }
}
