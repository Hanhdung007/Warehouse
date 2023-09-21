/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package warehouse.exam.demo.controllerAPI;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.reponsitory.ImportRepository;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.itemdataReponsitory;
import warehouse.exam.demo.reponsitory.supplierRepository;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.ImportService;
import warehouse.exam.demo.service.itemdataService;
import warehouse.exam.demo.service.locationService;
import warehouse.exam.demo.service.supplierService;

/**
 *
 * @author DUNG
 */
@RestController
@RequestMapping("/api/importOrder")
public class ImportOrderAPIController {

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<importDAO> findAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Importorders getImport(@PathVariable(value = "id") int id) {
        Importorders imp = service.findOne(id);
        return imp;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Importorders create(@RequestBody importDAO impDAO) {
        return service.saveImportOrder(impDAO);
    }

    @PostMapping("/v2/createItem")
    public ResponseEntity createVersion2(@RequestBody itemmasterDAO ItemmasterDAO) {
        Importorders imp = impRepository.findById(ItemmasterDAO.getIdImport());
        Itemmasters item = new Itemmasters();
        item.setCodeItemdata(imDataRepository.findByName(ItemmasterDAO.getItemName()));
        item.setDateImport(ItemmasterDAO.getDateImport());
        item.setIdImport(imp);
        item.setNote(ItemmasterDAO.getNote());
        item.setQcBy("");
        item.setRecieveNo(ItemmasterDAO.getRecieveNo());
        item.setSupId(supReponsitory.findBySupName(ItemmasterDAO.getSupplierName()));
        item.setQuantity(ItemmasterDAO.getQuantity());
        item.setQcAcceptQuantity(0.0);
        item.setPass(false);
        item.setDisable(false);
        item.setQcInjectQuantity(0.0);
        item.setLocationCode("");
        item.setBookQty(0.0);
        imMasterRepositoty.save(item);
        return ResponseEntity.ok(200);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Itemmasters create(@RequestBody itemmasterDAO ItemmasterDAO, @PathVariable(value = "id") int id, @RequestBody Itemmasters item) {
        return itemmasterService.saveItemMaster(ItemmasterDAO, id, item);
    }

//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Importorders update(@PathVariable(value = "id") int id, @RequestBody importDAO impDAO) {
//        return impService.updateImpOrder(impDAO, id);
//    }
}
