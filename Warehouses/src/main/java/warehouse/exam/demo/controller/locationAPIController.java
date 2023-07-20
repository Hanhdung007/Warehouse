/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package warehouse.exam.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import warehouse.exam.demo.DAL.locationDAO;
import warehouse.exam.demo.model.Locations;
import warehouse.exam.demo.service.locationService;

/**
 *
 * @author DUNG
 */
@RestController
@RequestMapping("/api/location")
public class locationAPIController {
    @Autowired
    locationService locService;
    
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<locationDAO> get(){
        return locService.getAll();
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Locations create(@RequestBody locationDAO newLocations) {
        return locService.saveLocation(newLocations);
    }
//    @GetMapping("/{code}")
//    @ResponseStatus(HttpStatus.OK)
//    public Locations getWarehouse(@PathVariable(value = "code") String code) {
//        Locations warehouse = locService.findOne(code);
//        return warehouse;
//    }
//    @PostMapping("/update")
//    @ResponseStatus(HttpStatus.OK)
//    public Locations updateWarehouse(@RequestBody locationDAO warehouses) {
//        return locService.updateLocation(warehouses.getCode(), warehouses);
//    }
}
