/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.DAL.locationDAO;
import warehouse.exam.demo.model.Locations;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.locationReponsitory;
import warehouse.exam.demo.reponsitory.warehouseRepository;

/**
 *
 * @author DUNG
 */
@Service
public class locationService {

    @Autowired
    locationReponsitory locReponsitory;
    @Autowired
    warehouseRepository whReponsitory;
    @Autowired
    ItemmasterRepository itemmasterRepository;

    public List<locationDAO> getAll() {
        List<locationDAO> dao = new ArrayList<>();
        List<Locations> location = locReponsitory.findAll();
        for (Locations loc : location) {
            locationDAO locdao = new locationDAO();
            locdao.setCode(loc.getCode());
            locdao.setName(loc.getName());
            locdao.setCapacity(loc.getCapacity());
            locdao.setRemain(loc.getRemain());
            locdao.setWarehouseName(loc.getWarehouseCode().getName());
            locdao.setActive(loc.getActive());
            dao.add(locdao);
        }
        return dao;
    }
    public List<locationDAO> pickListLocation() {
        List<locationDAO> dao = new ArrayList<>();
        List<Locations> location = locReponsitory.getRemainLocation();
        for (Locations loc : location) {
            locationDAO locdao = new locationDAO();
            locdao.setCode(loc.getCode());
            locdao.setName(loc.getName());
            locdao.setCapacity(loc.getCapacity());
            locdao.setRemain(loc.getRemain());
            locdao.setWarehouseName(loc.getWarehouseCode().getName());
            locdao.setActive(loc.getActive());
            dao.add(locdao);
        }
        return dao;
    }

    public Locations saveLocation(locationDAO newLocations) {
        Locations location = new Locations();
        location.setCode(newLocations.getCode());
        location.setName(newLocations.getName());
        location.setActive(newLocations.isActive());
        location.setCapacity(newLocations.getCapacity());
        location.setRemain(newLocations.getCapacity());
        location.setWarehouseCode(whReponsitory.findByCode(newLocations.getWarehouseCode()));
        return locReponsitory.save(location);
    }

    public Locations findOne(String code) {
        return locReponsitory.findByCode(code);
    }

    public Locations updateLocation(String code, locationDAO newLocations) {
        Locations location = locReponsitory.findByCode(code);
        location.setName(newLocations.getName());
        location.setActive(newLocations.isActive());
        location.setCapacity(newLocations.getCapacity());
        location.setWarehouseCode(whReponsitory.findByCode(newLocations.getWarehouseCode()));
        return locReponsitory.save(location);
    }
//    public List<itemmasterDAO> getItem(){
//        List<locationDAO> dao = new ArrayList<>();
//        List<Locations> location = locReponsitory.findAll();
//        
//    }
}
