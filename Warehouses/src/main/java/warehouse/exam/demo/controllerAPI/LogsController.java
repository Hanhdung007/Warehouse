/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package warehouse.exam.demo.controllerAPI;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.DAL.itemmasterDAO;
import warehouse.exam.demo.DAL.logDAO;
import warehouse.exam.demo.service.ItemmasterService;
import warehouse.exam.demo.service.LogService;

/**
 *
 * @author DUNG
 */
@RestController
@RequestMapping("/api/logs")
public class LogsController {
      @Autowired
    LogService logService;
      
    @GetMapping("/getlogs")
    public List<logDAO> getItem(){
        return logService.getAll();
    }

}
