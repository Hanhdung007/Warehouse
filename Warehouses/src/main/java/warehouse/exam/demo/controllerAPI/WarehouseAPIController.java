/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package warehouse.exam.demo.controllerAPI;

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
import warehouse.exam.demo.DAL.warehouseDAO;
import warehouse.exam.demo.model.Warehouses;
import warehouse.exam.demo.service.warehouseService;

/**
 *
 * @author DUNG
 */
@RestController
@RequestMapping("/api/warehouse")
public class WarehouseAPIController {

    @Autowired
    warehouseService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<warehouseDAO> findAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Warehouses Create(@RequestBody warehouseDAO warehouse) {
        return service.saveWarehouse(warehouse);
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Warehouses getWarehouse(@PathVariable(value = "code") String code) {
        Warehouses warehouse = service.findbycode(code);
        return warehouse;
    }
    @PutMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Warehouses updateWarehouse(@PathVariable(value = "code") String code, @RequestBody warehouseDAO warehouses) {
        return service.updateWarehouse(code, warehouses);
    }
}
