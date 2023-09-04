/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

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
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.allocateRepository;
import warehouse.exam.demo.reponsitory.locationReponsitory;
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

    @GetMapping("/requests")
    public String allocateRequest(Model model) {
        model.addAttribute("list", service.getAllocateOrder());
        return "/itemmaster/allocateRequest";
    }

    @GetMapping("/confirmAllocate/{id}")
    public ResponseEntity confirmAllocate(@PathVariable("id") int id) {
        /*
            1. Lấy ra AllocateOrder theo id truyền về
            2. Lấy ra ItemMaster dựa trên itemMasterId AllocateOrder 
            3. Cập nhật location code cho ItemMaster đó = location code của Allocate Order
            4. Lấy location dựa vào location code của AllocateOrder
            5. Lấy quantity của allocate order trừ vào remain của location 
        vd: location.setRemain(location.getRemain - qty)
            6. Cập nhật Allocate Order Confirm = true
           7. Gọi hàm save cho Allocate Order , ItemMaster , Location
         */
          AllocateOrder allocate = allocateRepository.findById(id);
            Itemmasters item = itemMasterReponsitory.findById(allocate.getItemMasterId()).get();
           List<AllocateOrder> currentAllocate = allocateRepository.finbyItemAndLocation(allocate.getLocationCode(),allocate.getItemMasterId(),allocate.getId());
          // 1.Kiểm tra coi allocate Order  có ItemMasterId và locationCode của pickList hay chưa và id != id truyền về từ controller
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
            newItem.setLocationCode(allocate.getLocationCode());
            itemMasterReponsitory.save(newItem);
        }else{
            item.setLocationCode(allocate.getLocationCode());
            item.setQuantity(allocate.getQuantity());
            item.setQcAcceptQuantity(allocate.getQuantity());
            item.setBookQty(allocate.getQuantity());
        }
      
        Locations location = locationReponsitory.findByCode(allocate.getLocationCode());
       
        location.setRemain(location.getRemain() - allocate.getQuantity());
        allocate.setConfirm(true);
        allocateRepository.save(allocate);
        itemMasterReponsitory.save(item);
        locationReponsitory.save(location);
        return ResponseEntity.ok(200);
    }

}
