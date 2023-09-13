/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import warehouse.exam.demo.DAL.PickListDAO;
import warehouse.exam.demo.model.AllocateOrder;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.model.Locations;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.allocateRepository;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.locationService;
import org.springframework.web.bind.annotation.*;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.DAL.itemmasterDAO;
// import warehouse.exam.demo.service.IetmmasterService;

import java.text.ParseException;
import java.util.Optional;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/itemmaster")
public class ItemmasterController {

    @Autowired
    ItemmasterService service;
    @Autowired
    locationService locService;

    @Autowired
    ItemmasterRepository itemMasterReponsitory;

    @Autowired
    allocateRepository AllocateOrderReponsitory;

    @GetMapping("/unallocate")
    public String unallocate(Model model) {
        model.addAttribute("list", service.unallocate());
        return "itemmaster/unallocate";
    }

    @GetMapping("/picklist/{id}")
    public String picklist(Model model, @PathVariable(value = "id") int id) {
        Optional<Itemmasters> item = service.findOne(id);
        model.addAttribute("item", item);
        model.addAttribute("location", locService.pickListLocation());
        return "itemmaster/pickList";
    }

    @PostMapping(value = "/confirmPickList")
    public ResponseEntity confirmPickList(@RequestBody PickListDAO pickList) {
        
        //Check remain của location tính pick list còn đủ số lượng hay k 
        Locations loc = locService.findOne(pickList.getLocationCode());
        if(loc.getRemain() < pickList.getQty()){
            return ResponseEntity.ok(300);
        }
        Itemmasters oldItem = itemMasterReponsitory.findById(pickList.getItemMasterId()).get();
        oldItem.setBookQty(oldItem.getBookQty()+pickList.getQty());
        itemMasterReponsitory.save(oldItem);

        AllocateOrder allocateOrder = new AllocateOrder();
        LocalDateTime ldt = LocalDateTime.now();
        Instant instant = ldt.toInstant(ZoneOffset.UTC);
        Date date = Date.from(instant);
        allocateOrder.setConfirm(false);
        allocateOrder.setCreatedDate(date);
        allocateOrder.setItemMasterId(pickList.getItemMasterId());
        allocateOrder.setLocationCode(pickList.getLocationCode());
        allocateOrder.setQuantity(pickList.getQty());
        AllocateOrderReponsitory.save(allocateOrder);
        return ResponseEntity.ok(200);
    }
    //IetmmasterService service;
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
