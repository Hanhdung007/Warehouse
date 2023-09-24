/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controllerAPI;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.service.ItemmasterService;

/**
 *
 * @author DUNG
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryApiController {
    
     @Autowired
    ItemmasterService itemMasterService;
    @GetMapping("/getdata")
    @ResponseStatus(HttpStatus.OK)
    public List<itemmasterDAO> getItem(){
        return itemMasterService.findAllInventorys();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Itemmasters getSingleItem(@PathVariable(value = "id") int id){
        return itemMasterService.findOne(id);
    }
}
