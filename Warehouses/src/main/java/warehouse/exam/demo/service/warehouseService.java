/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.warehouseDAO;
import warehouse.exam.demo.model.Warehouses;
import warehouse.exam.demo.reponsitory.warehouseRepository;

/**
 *
 * @author DUNG
 */
@Service
public class warehouseService {

    @Autowired
    warehouseRepository whReponsitory;

    public List<warehouseDAO> getAll() {
        List<warehouseDAO> dao = new ArrayList<>();
        List<Warehouses> whs = whReponsitory.findAll();
        for (Warehouses wh : whs) {
            warehouseDAO whdao = new warehouseDAO();
            whdao.setCode(wh.getCode());
            whdao.setName(wh.getName());
            whdao.setActive(wh.getActive());
            dao.add(whdao);
        }
        return dao;
    }

    public Warehouses findbycode(String code) {
        return whReponsitory.findByCode(code);
    }

    public Warehouses saveWarehouse(warehouseDAO warehousesDAO) {
        Warehouses warehouse = new Warehouses();
        warehouse.setCode(warehousesDAO.getCode());
        warehouse.setName(warehousesDAO.getName());
        warehouse.setActive(warehousesDAO.isActive());
        return whReponsitory.save(warehouse);
    }

    public Warehouses updateWarehouse(String code,warehouseDAO warehousesDAO) {
        Warehouses warehouse = whReponsitory.findByCode(code);
        warehouse.setActive(warehousesDAO.isActive());
        return whReponsitory.save(warehouse);
    }
}
