/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.DAL.logDAO;
import warehouse.exam.demo.model.Log;
import warehouse.exam.demo.reponsitory.logRepository;

/**
 *
 * @author DUNG
 */
@Service
public class LogService {
    @Autowired
    logRepository logService;
    public List<logDAO> getAll() {
        List<logDAO> dao = new ArrayList<>();
        List<Log> log = logService.findAll();
        for (Log lg : log) {
            logDAO logdao = new logDAO();
            logdao.setId(lg.getId());
            logdao.setItemName(lg.getItemmasterId().getCodeItemdata().getName());
            logdao.setLocationName(lg.getLocationName());
            logdao.setRecieveNo(lg.getItemmasterId().getRecieveNo());
            logdao.setMethod(lg.getMethod());
            logdao.setQuantity(lg.getQuantity());
            logdao.setSaveDate(lg.getSaveDate());
            dao.add(logdao);
        }
        return dao;
    }
}
