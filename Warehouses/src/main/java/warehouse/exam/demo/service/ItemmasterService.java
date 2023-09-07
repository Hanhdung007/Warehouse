/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.model.Importorders;
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.reponsitory.ImportRepository;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.itemdataReponsitory;
import warehouse.exam.demo.reponsitory.locationReponsitory;
import warehouse.exam.demo.reponsitory.supplierRepository;

/**
 *
 * @author DUNG
 */
@Service
public class ItemmasterService {

    @Autowired
    ItemmasterRepository imMasterRepositoty;
    @Autowired
    ImportRepository impRepository;
    @Autowired
    itemdataReponsitory imDataRepository;
    @Autowired
    locationReponsitory locRepository;
    @Autowired
    supplierRepository supReponsitory;

    public List<itemmasterDAO> getAll() throws ParseException {
        List<itemmasterDAO> dao = new ArrayList<>();
        List<Itemmasters> list = imMasterRepositoty.findAll();
        for (Itemmasters im : list) {
            itemmasterDAO imtDao = new itemmasterDAO();
            imtDao.setDateImport(im.getDateImport());
            imtDao.setId(im.getId());
            imtDao.setIdImport(im.getIdImport().getId());
            imtDao.setItemName(im.getCodeItemdata().getName());
            //query location name 
            //check itemmaster location code (allocated)
            if (!im.getLocationCode().isEmpty() && im.getLocationCode() != null) {
                imtDao.setLocationName(locRepository.findByCode(im.getLocationCode()).getName());
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
            dao.add(imtDao);
        }
        return dao;
    }

    public List<itemmasterDAO> unallocate() {
        List<itemmasterDAO> dao = new ArrayList<>();
        List<Itemmasters> list = imMasterRepositoty.GetUnAllocated();
        for (Itemmasters im : list) {
            itemmasterDAO imtDao = new itemmasterDAO();
            imtDao.setDateImport(im.getDateImport());
            imtDao.setId(im.getId());
            imtDao.setIdImport(im.getIdImport().getId());
            imtDao.setItemName(im.getCodeItemdata().getName());
            //query location name 
            //check itemmaster location code (allocated)
            if (!im.getLocationCode().isEmpty() && im.getLocationCode() != null) {
                imtDao.setLocationName(locRepository.findByCode(im.getLocationCode()).getName());
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
        return dao;
    }

   
     public Itemmasters saveItemMaster(itemmasterDAO itemDAO, int id, Itemmasters item) {
          Importorders imp = impRepository.findById(id);
          item.setId(itemDAO.getId());
          item.setCodeItemdata(imDataRepository.findByName(itemDAO.getItemName()));
          item.setDateImport(itemDAO.getDateImport());
          item.setIdImport(imp);
//          item.setLocationCode(itemmasterDAO.);
          item.setNote(itemDAO.getNote());
          item.setQcAcceptQuantity(itemDAO.getQcAcceptQuantity());
          item.setQcBy(itemDAO.getQcBy());
          item.setRecieveNo(itemDAO.getRecieveNo());
          item.setSupId(supReponsitory.findBySupName(itemDAO.getSupplierName()));
          item.setQuantity(itemDAO.getQuantity());
          item.setDisable(itemDAO.getDisable());
          return imMasterRepositoty.save(item);
    }

    public Itemmasters findOne(int code) {
        return imMasterRepositoty.findById(code).get();
    }

    public Itemmasters update(int id, itemmasterDAO updateItem) {
        Itemmasters item = imMasterRepositoty.findById(id).get();
        item.setQuantity(updateItem.getQuantity());
        item.setRecieveNo(updateItem.getRecieveNo());
        item.setDateImport(updateItem.getDateImport());
        item.setNote(updateItem.getNote());
        item.setQcBy(updateItem.getQcBy());
        item.setCodeItemdata(imDataRepository.findByName(updateItem.getItemName()));
        item.setDisable(updateItem.getDisable());
        return imMasterRepositoty.save(item);
    }
}
