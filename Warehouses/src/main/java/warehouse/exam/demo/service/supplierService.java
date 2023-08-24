/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.supplierDAO;
import warehouse.exam.demo.model.Supplier;
import warehouse.exam.demo.reponsitory.supplierRepository;

/**
 *
 * @author DUNG
 */
@Service
public class supplierService {

    @Autowired
    supplierRepository supRepository;

    public List<supplierDAO> getAll() {
        List<supplierDAO> dao = new ArrayList<>();
        List<Supplier> whs = supRepository.findAll();
        for (Supplier wh : whs) {
            supplierDAO whdao = new supplierDAO();
            whdao.setSupId(wh.getSupId());
            whdao.setActive(wh.getActive());
            whdao.setCity(wh.getCity());
            whdao.setSupAddress(wh.getSupAddress());
            whdao.setSupEmail(wh.getSupEmail());
            whdao.setSupName(wh.getSupName());
            whdao.setTaxCode(wh.getTaxCode());
            dao.add(whdao);
        }
        return dao;
    }

    public Supplier findbycode(String code) {
        return supRepository.findById(code).get();
    }

    public Supplier saveSupplier(supplierDAO supDAO) {
        Supplier supplier = new Supplier();
        supplier.setSupId(supDAO.getSupId());
        supplier.setActive(supDAO.getActive());
        supplier.setCity(supDAO.getCity());
        supplier.setSupAddress(supDAO.getSupAddress());
        supplier.setSupEmail(supDAO.getSupEmail());
        supplier.setSupName(supDAO.getSupName());
        supplier.setTaxCode(supDAO.getTaxCode());
        return supRepository.save(supplier);
    }
    public Supplier updateSupplier(String code, supplierDAO supDAO) {
        Supplier supplier = supRepository.findById(code).get();
        supplier.setSupId(supDAO.getSupId());
        supplier.setActive(supDAO.getActive());
        supplier.setCity(supDAO.getCity());
        supplier.setSupAddress(supDAO.getSupAddress());
        supplier.setSupEmail(supDAO.getSupEmail());
        supplier.setSupName(supDAO.getSupName());
        supplier.setTaxCode(supDAO.getTaxCode());
        return supRepository.save(supplier);
    }
}
