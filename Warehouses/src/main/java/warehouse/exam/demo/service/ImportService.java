/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.reponsitory.ImportRepository;

/**
 *
 * @author DUNG
 */
@Service
public class ImportService {

    @Autowired
    ImportRepository importReponsitory;

    public List<importDAO> getAll() {
        List<importDAO> dao = new ArrayList<>();
        List<Importorders> imports = importReponsitory.findAll();
        for (Importorders list : imports) {
            importDAO imp = new importDAO();
            imp.setId(list.getId());
            imp.setDriver(list.getDriver());
            imp.setDriversPhone(list.getDriversPhone());
            imp.setFactory(list.getFactory());
            imp.setDateImport(list.getDateImport());
            imp.setNote(list.getNote());
            imp.setStatus(list.getStatus());
            dao.add(imp);
        }
        return dao;
    }

    public Importorders saveImportOrder(importDAO importDAO) {
        Importorders impOrder = new Importorders();
        impOrder.setId(impOrder.getId());
        impOrder.setDriver(importDAO.getDriver());
        impOrder.setDateImport(importDAO.getDateImport());
        impOrder.setDriversPhone(importDAO.getDriversPhone());
        impOrder.setFactory(importDAO.getFactory());
        impOrder.setNote(importDAO.getNote());
        impOrder.setStatus(importDAO.getStatus());
        return importReponsitory.save(impOrder);
    }

    public Importorders findOne(int id) {
        return importReponsitory.findById(id).get();
    }

    public Importorders updateImpOrder(importDAO importDAO, int id) {
        Importorders impOrder = importReponsitory.findById(id).get();
        impOrder.setId(impOrder.getId());
        impOrder.setDriver(importDAO.getDriver());
        impOrder.setDateImport(importDAO.getDateImport());
        impOrder.setDriversPhone(importDAO.getDriversPhone());
        impOrder.setFactory(importDAO.getFactory());
        impOrder.setNote(importDAO.getNote());
        impOrder.setStatus(importDAO.getStatus());
        return importReponsitory.save(impOrder);
    }
}
