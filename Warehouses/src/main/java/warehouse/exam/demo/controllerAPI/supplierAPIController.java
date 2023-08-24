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
import warehouse.exam.demo.DAL.supplierDAO;
import warehouse.exam.demo.model.Supplier;
import warehouse.exam.demo.service.supplierService;

/**
 *
 * @author DUNG
 */
@RestController
@RequestMapping("/api/supplier")
public class supplierAPIController {
    @Autowired
    supplierService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<supplierDAO> findAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier Create(@RequestBody supplierDAO supplierDAO) {
        return service.saveSupplier(supplierDAO);
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier getWarehouse(@PathVariable(value = "code") String code) {
        Supplier supplier = service.findbycode(code);
        return supplier;
    }
    @PutMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier updateWarehouse(@PathVariable(value = "code") String code, @RequestBody supplierDAO supplierDAO) {
        return service.updateSupplier(code, supplierDAO);
    }
}
