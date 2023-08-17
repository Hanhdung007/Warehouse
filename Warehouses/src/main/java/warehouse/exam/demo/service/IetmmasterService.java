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
import warehouse.exam.demo.model.Itemmasters;
import warehouse.exam.demo.reponsitory.ImportRepository;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.itemdataReponsitory;
import warehouse.exam.demo.reponsitory.locationReponsitory;

/**
 *
 * @author DUNG
 */
@Service
public class IetmmasterService {

    @Autowired
    ItemmasterRepository imMasterRepositoty;
    @Autowired
    ImportRepository impRepository;
    @Autowired
    itemdataReponsitory imDataRepository;
    @Autowired
    locationReponsitory locRepository;

    public List<itemmasterDAO> getAll() {
        List<itemmasterDAO> dao = new ArrayList<>();
        List<Itemmasters> list = imMasterRepositoty.findAll();
        for (Itemmasters im : list) {
            itemmasterDAO imtDao = new itemmasterDAO();
            imtDao.setDateImport(im.getDateImport());
            imtDao.setId(im.getId());
            imtDao.setIdImport(im.getIdImport());
            imtDao.setItemName(im.getCodeItemdata().getName());
            imtDao.setLocationName(im.getLocationCode().getName());
            imtDao.setNote(im.getNote());
            imtDao.setQcAcceptQuantity(im.getQcAcceptQuantity());
            imtDao.setQcBy(im.getQcBy());
            imtDao.setQuantity(im.getQuantity());
            imtDao.setRecieveNo(im.getRecieveNo());
            dao.add(imtDao);
        }
        return dao;
    }
}
