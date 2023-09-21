/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import warehouse.exam.demo.model.AllocateOrder;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.model.Locations;
import warehouse.exam.demo.model.Log;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.allocateRepository;
import warehouse.exam.demo.reponsitory.locationReponsitory;
import warehouse.exam.demo.reponsitory.logRepository;
import warehouse.exam.demo.service.AllocateService;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.locationService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/allocate")
public class AllocateController {

    @Autowired
    AllocateService service;
    @Autowired
    ItemmasterRepository itemMasterReponsitory;
    @Autowired
    locationReponsitory locationReponsitory;
    @Autowired
    allocateRepository allocateRepository;
    @Autowired
    logRepository logRepository;

    @GetMapping("/requests")
    public String allocateRequest(Model model) {
        model.addAttribute("list", service.getAllocateOrder());
        return "/itemmaster/allocateRequest";
    }

    @GetMapping("/confirmAllocate/{id}")
    public ResponseEntity confirmAllocate(@PathVariable("id") int id) {

        AllocateOrder allocate = allocateRepository.findById(id);
        Locations location = locationReponsitory.findByCode(allocate.getLocationCode());
        if (location.getRemain() < allocate.getQuantity()) {
            return ResponseEntity.ok(300);
        }
        location.setRemain(location.getRemain() - allocate.getQuantity());
        allocate.setConfirm(true);
        Itemmasters item = itemMasterReponsitory.findById(allocate.getItemMasterId()).get();
        List<AllocateOrder> currentAllocate = allocateRepository.finbyItemAndLocation(allocate.getLocationCode(), allocate.getItemMasterId(), allocate.getId());
        // 1.Kiểm tra coi allocate Order  có ItemMasterId và locationCode của pickList hay chưa và id != id truyền về từ controller
        // Save Log 
        Log log = new Log();
        if (!currentAllocate.isEmpty()) {
            // tạo mới item masters, gán mọi thông tin từ oldItem sang newItem (trừ các biến lqty, qty gán = pickList.getQty())
            Itemmasters newItem = new Itemmasters();
            newItem.setCodeItemdata(item.getCodeItemdata());
            newItem.setDateImport(item.getDateImport());
            newItem.setIdImport(item.getIdImport());
            newItem.setNote(item.getNote());
            newItem.setQcBy(item.getQcBy());
            newItem.setBookQty(allocate.getQuantity());
            newItem.setQuantity(allocate.getQuantity());
            newItem.setQcAcceptQuantity(allocate.getQuantity());
            newItem.setRecieveNo(item.getRecieveNo());
            newItem.setSupId(item.getSupId());
            newItem.setQcInjectQuantity(0.0);
            newItem.setPass(item.getPass());
            newItem.setDisable(item.getDisable());
            newItem.setLocationCode(allocate.getLocationCode());
            itemMasterReponsitory.save(newItem);
            log.setItemmasterId(newItem);
        } else {
            item.setLocationCode(allocate.getLocationCode());
            item.setQuantity(allocate.getQuantity());
            item.setQcAcceptQuantity(allocate.getQuantity());
            item.setBookQty(allocate.getQuantity());
            log.setItemmasterId(item);
        }
        
        log.setLocationName(location.getName());
        log.setMethod("Allocate");
        log.setQuantity(allocate.getQuantity());
         LocalDateTime ldt = LocalDateTime.now();
        Instant instant = ldt.toInstant(ZoneOffset.UTC);
        Date date = Date.from(instant);
        log.setSaveDate(date);
        logRepository.save(log);

        allocateRepository.save(allocate);
        itemMasterReponsitory.save(item);
        locationReponsitory.save(location);
        return ResponseEntity.ok(200);
    }

}
