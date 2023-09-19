/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.importDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Supplier;
import warehouse.exam.demo.reponsitory.ImportRepository;
import warehouse.exam.demo.reponsitory.supplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author DUNG
 */
@Service
public class ImportService {

    @Autowired
    ImportRepository importReponsitory;
    @Autowired
    supplierRepository supReponsity;

    public List<importDAO> getAll() {
        List<importDAO> dao = new ArrayList<>();
        List<Importorders> imports = importReponsitory.findAll();
        for (Importorders list : imports) {
            importDAO imp = new importDAO();
            imp.setId(list.getId());
            imp.setDriver(list.getDriver());
            imp.setDriversPhone(list.getDriversPhone());
            imp.setSupplierName(list.getSupId().getSupName());
            imp.setDateImport(list.getDateImport());
            imp.setNote(list.getNote());
            imp.setStatus(list.getStatus());
            dao.add(imp);
        }
        return dao;
    }

    public List<importDAO> searchImports(String keyword) {
        return importReponsitory.searchAllImport(keyword);
    }

    public Importorders saveImportOrder(importDAO importDAO) {
        Importorders impOrder = new Importorders();
//        impOrder.setId(importDAO.getId());
        impOrder.setDriver(importDAO.getDriver());
        impOrder.setDateImport(importDAO.getDateImport());
        impOrder.setDriversPhone(importDAO.getDriversPhone());
        impOrder.setSupId(supReponsity.findBySupName(importDAO.getSupplierName()));
        impOrder.setNote(importDAO.getNote());
        impOrder.setStatus(importDAO.getStatus());
        return importReponsitory.save(impOrder);
    }

    public Importorders findOne(int id) {
        return importReponsitory.findById(id);
    }

    public Importorders updateImpOrder(importDAO impDAO) {
        Optional<Importorders> optionalImp = importReponsitory.findById(impDAO.getId());
        Optional<Supplier> IdSup = supReponsity.findById(impDAO.getSupId());
        if (!IdSup.isPresent()) {
            throw new RuntimeException("Can Not Find Supplier!");
        }
        if (optionalImp.isPresent()) {
            Importorders imp = optionalImp.get();
            // Kiểm tra các trường trong impDAO và cập nhật imp tương ứng
            if (impDAO.getId() != null) {
                imp.setId(impDAO.getId());
            }
            if (impDAO.getDriver() != null) {
                imp.setDriver(impDAO.getDriver());
            }
            if (impDAO.getDriversPhone() != null) {
                imp.setDriversPhone(impDAO.getDriversPhone());
            }
            if (impDAO.getSupId() != null) {
                imp.setSupId(IdSup.get());
            }
            if (impDAO.getDateImport() != null) {
                imp.setDateImport(impDAO.getDateImport());
            }
            if (impDAO.getNote() != null) {
                imp.setNote(impDAO.getNote());
            }
            if(impDAO.getStatus() != null) {
                imp.setStatus(impDAO.getStatus());
            }
            return importReponsitory.save(imp);
        } else {
            throw new RuntimeException("Không tìm thấy Import với ID = " + impDAO.getId());
        }
    }
}
