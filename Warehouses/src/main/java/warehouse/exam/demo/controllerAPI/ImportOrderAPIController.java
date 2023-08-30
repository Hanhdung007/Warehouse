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
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.service.ImportService;

/**
 *
 * @author DUNG
 */
@RestController
@RequestMapping("/api/importOrder")
public class ImportOrderAPIController {
    @Autowired
    ImportService impService;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<importDAO> findAll(){
        return impService.getAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Importorders findOne(@PathVariable(value = "id") int id){
        Importorders imp = impService.findOne(id);
        return imp;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Importorders create(@RequestBody importDAO impDAO){
        return impService.saveImportOrder(impDAO);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Importorders update(@PathVariable(value = "id") int id, @RequestBody importDAO impDAO){
        return impService.updateImpOrder(impDAO, id);
    }
}
