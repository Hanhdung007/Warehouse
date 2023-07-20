/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.itemdataDAO;
import warehouse.exam.demo.model.Itemdata;
import warehouse.exam.demo.reponsitory.itemdataReponsitory;

/**
 *
 * @author DUNG
 */
@Service
public class itemdataService {

    @Autowired
    itemdataReponsitory itemreponsitory;

    public List<itemdataDAO> getAll() {
        List<itemdataDAO> itemdatalist = new ArrayList<>();
        List<Itemdata> list = itemreponsitory.findAll();
        for (Itemdata item : list) {
            itemdataDAO items = new itemdataDAO();
            items.setCode(item.getCode());
            items.setName(item.getName());
            items.setColor(item.getColor());
            items.setType(item.getType());
            items.setActive(item.getActive());
            items.setImage(item.getImage());
            itemdatalist.add(items);
        }
        return itemdatalist;
    }

    public Itemdata saveItemData(itemdataDAO itemDAO) {
        Itemdata items = new Itemdata();
        items.setCode(itemDAO.getCode());
        items.setName(itemDAO.getName());
        items.setColor(itemDAO.getColor());
        items.setType(itemDAO.getType());
        items.setActive(itemDAO.getActive());
        items.setImage(itemDAO.getImage());
        return itemreponsitory.save(items);
    }

    public Itemdata updateItemData(String code, itemdataDAO itemdataDAO) {
        Itemdata itemdata = itemreponsitory.findById(code).get();
        itemdata.setName(itemdataDAO.getName());
        itemdata.setColor(itemdataDAO.getColor());
        itemdata.setType(itemdataDAO.getType());
        itemdata.setActive(itemdataDAO.getActive());
        itemdata.setImage(itemdataDAO.getImage());
        return itemreponsitory.save(itemdata);
    }
}
