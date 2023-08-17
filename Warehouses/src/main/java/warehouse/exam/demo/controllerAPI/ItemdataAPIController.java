/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package warehouse.exam.demo.controllerAPI;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import warehouse.exam.demo.DAL.itemdataDAO;
import warehouse.exam.demo.model.Itemdatas;
import warehouse.exam.demo.service.itemdataService;

/**
 *
 * @author DUNG
 */
@RestController
@RequestMapping("/api/itemdata")
public class ItemdataAPIController {
    @Autowired
    itemdataService service;
    
    @GetMapping()
    public List<itemdataDAO> list() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public Itemdatas get(@PathVariable("id") String code) {
        Itemdatas item = service.findOne(code);
        return item;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Itemdatas Create(@RequestBody itemdataDAO itemdata) {
        return service.saveItemData(itemdata);
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Itemdatas getWarehouse(@PathVariable(value = "code") String code) {
        Itemdatas warehouse = service.findOne(code);
        return warehouse;
    }
    @PutMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Itemdatas updateWarehouse(@PathVariable(value = "code") String code, @RequestBody itemdataDAO itemdata) {
       return service.updateItemData(itemdata, code);
    } 
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
