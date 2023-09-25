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
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.model.Locations;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.itemdataReponsitory;
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
    @Autowired
    itemdataReponsitory itemdataRepository;

    public locationDAO findByLocationCode(String code) {
        Locations loc = locReponsitory.findByCode(code);
        locationDAO locdao = new locationDAO();
        locdao.setCode(loc.getCode());
        locdao.setName(loc.getName());
        locdao.setCapacity(loc.getCapacity());
        locdao.setRemain(loc.getRemain());
        locdao.setWarehouseName(loc.getWarehouseCode().getName());
        locdao.setActive(loc.getActive());
        List<itemmasterDAO> dao = new ArrayList<>();
        List<Itemmasters> itemMasters = itemmasterRepository.findItemsByLocationsCode(code);
        for (Itemmasters im : itemMasters) {
            itemmasterDAO imtDao = new itemmasterDAO();
            imtDao.setDateImport(im.getDateImport());
            imtDao.setId(im.getId());
            imtDao.setIdImport(im.getIdImport().getId());
            imtDao.setItemName(im.getCodeItemdata().getName());
            //query location name 
            //check itemmaster location code (allocated)
            if (!im.getLocationCode().isEmpty() && im.getLocationCode() != null) {
                imtDao.setLocationName(locReponsitory.findByCode(im.getLocationCode()).getName());
            } else {
                //unallocate
                imtDao.setLocationName("");
            }

            imtDao.setNote(im.getNote());
            imtDao.setQcAcceptQuantity(im.getQcAcceptQuantity());
            imtDao.setQcBy(im.getQcBy());
            imtDao.setQuantity(im.getQuantity());
            imtDao.setRecieveNo(im.getRecieveNo());
            imtDao.setSupplierName(im.getSupId().getSupName());
            imtDao.setImage(im.getCodeItemdata().getImage());
            dao.add(imtDao);
        }
        locdao.setItems(dao);
        return locdao;
    }

    public List<locationDAO> getAll() {
        List<locationDAO> dao = new ArrayList<>();
        List<itemmasterDAO> itemdal = new ArrayList<>();
        List<Itemmasters> itemMasters = itemmasterRepository.findAll();
        for (Itemmasters im : itemMasters) {
            itemmasterDAO imtDao = new itemmasterDAO();
            imtDao.setDateImport(im.getDateImport());
            imtDao.setId(im.getId());
            imtDao.setIdImport(im.getIdImport().getId());
            imtDao.setItemName(im.getCodeItemdata().getName());
            //query location name 
            //check itemmaster location code (allocated)
            if (!im.getLocationCode().isEmpty() && im.getLocationCode() != null) {
                imtDao.setLocationName(locReponsitory.findByCode(im.getLocationCode()).getName());
            } else {
                //unallocate
                imtDao.setLocationName("");
            }

            imtDao.setNote(im.getNote());
            imtDao.setQcAcceptQuantity(im.getQcAcceptQuantity());
            imtDao.setQcBy(im.getQcBy());
            imtDao.setQuantity(im.getQuantity());
            imtDao.setRecieveNo(im.getRecieveNo());
            imtDao.setSupplierName(im.getSupId().getSupName());
            imtDao.setImage(im.getCodeItemdata().getImage());
            imtDao.setCodeItemdata(im.getCodeItemdata().getCode());
            imtDao.setPass(im.getPass());
            imtDao.setDisable(im.getDisable());
            imtDao.setQcInjectQuantity(im.getQcInjectQuantity());
            itemdal.add(imtDao);
        }
        List<Locations> location = locReponsitory.findAll();
        for (Locations loc : location) {
            locationDAO locdao = new locationDAO();
            locdao.setCode(loc.getCode());
            locdao.setName(loc.getName());
            locdao.setCapacity(loc.getCapacity());
            locdao.setRemain(loc.getRemain());
            locdao.setWarehouseName(loc.getWarehouseCode().getName());
            locdao.setActive(loc.getActive());
            locdao.setWarehouseCode(loc.getWarehouseCode().getCode());
            locdao.setItems(itemdal);
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
        if (location.getCode().isEmpty()) {
            location.setCode(newLocations.getCode());
        }
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

    public List<Locations> findLocationByWarehouse(String warehouseCode) {
        return locReponsitory.getLocationByWarehouseCode(warehouseCode);
    }

    public Locations updateLocation(String code, locationDAO newLocations) {
        Locations location = locReponsitory.findByCode(code);
        location.setName(newLocations.getName());
        location.setActive(newLocations.isActive());
        location.setCapacity(newLocations.getCapacity());
        location.setWarehouseCode(whReponsitory.findByCode(newLocations.getWarehouseCode()));
        return locReponsitory.save(location);
    }
}
