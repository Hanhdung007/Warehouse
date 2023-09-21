/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.allocateOrderDAO;
import warehouse.exam.demo.model.AllocateOrder;
import warehouse.exam.demo.reponsitory.ItemmasterRepository;
import warehouse.exam.demo.reponsitory.allocateRepository;
import warehouse.exam.demo.reponsitory.itemdataReponsitory;
import warehouse.exam.demo.reponsitory.locationReponsitory;

/**
 *
 * @author DUNG
 */
@Service
public class AllocateService {

    @Autowired
    allocateRepository alcRepository;
    @Autowired
    locationReponsitory LocationReponsitory;
    @Autowired
    ItemmasterRepository itemmasterReponsitory;
    @Autowired
    itemdataReponsitory itemdataReponsitory;

    public List<allocateOrderDAO> getAllocateOrder() {
        List<allocateOrderDAO> dao = new ArrayList<>();
        List<AllocateOrder> list = alcRepository.GetAllocateOrderbyFalse();
        for (AllocateOrder im : list) {
            allocateOrderDAO allocateOrderDao = new allocateOrderDAO();
            allocateOrderDao.setId(im.getId());
            allocateOrderDao.setCreatedDate(im.getCreatedDate());
            allocateOrderDao.setItemName(itemmasterReponsitory.findById(im.getItemMasterId()).get().getCodeItemdata().getName());
            allocateOrderDao.setlocationName(LocationReponsitory.findByCode(im.getLocationCode()).getName());
            allocateOrderDao.setQuantity(im.getQuantity());
            allocateOrderDao.setConfirm(im.getConfirm());
           
            dao.add(allocateOrderDao);
        }
        return dao;
    }

    public AllocateOrder findOne(int id) {
        return alcRepository.findById(id);
    }
}
