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
import warehouse.exam.demo.model.Itemdatas;
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
        List<Itemdatas> list = itemreponsitory.findAll();
        for (Itemdatas item : list) {
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

    public Itemdatas saveItemData(itemdataDAO itemDAO) {
        Itemdatas items = new Itemdatas();
        items.setCode(itemDAO.getCode());
        items.setName(itemDAO.getName());
        items.setColor(itemDAO.getColor());
        items.setType(itemDAO.getType());
        items.setActive(itemDAO.getActive());
        if (itemDAO.getImage() != null) {
            items.setImage("img/" + itemDAO.getImage());
        }
        return itemreponsitory.save(items);
    }
    
     public Itemdatas updateItemData(itemdataDAO itemDAO, String code) {
         // tìm thằng cũ qua code
        Itemdatas items = itemreponsitory.findById(code).get();
        items.setName(itemDAO.getName());
        items.setColor(itemDAO.getColor());
        items.setType(itemDAO.getType());
        items.setActive(itemDAO.getActive());
        if (itemDAO.getImage() != null) {
            items.setImage("img/" + itemDAO.getImage());
        }
        return itemreponsitory.save(items);
    }

    public Itemdatas findOne(String code) {
        return itemreponsitory.findById(code).get();
    }
}
