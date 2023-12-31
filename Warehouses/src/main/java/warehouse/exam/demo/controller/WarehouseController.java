/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import warehouse.exam.demo.DAL.locationDAO;
import warehouse.exam.demo.DAL.warehouseDAO;
import warehouse.exam.demo.model.Locations;
import warehouse.exam.demo.model.Warehouses;
import warehouse.exam.demo.reponsitory.locationReponsitory;
import warehouse.exam.demo.reponsitory.warehouseRepository;
import warehouse.exam.demo.service.warehouseService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    warehouseService service;
    @Autowired
    warehouseRepository whReponsitory;
    @Autowired
    locationReponsitory locReponsitory;

    @GetMapping("/index")
    @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
    public String page(Model model) {
        model.addAttribute("list", service.getAll());
        return "warehouse/index";
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
    public String details(Model model, @PathVariable("id") String id) {
        model.addAttribute("model", service.findbycode(id));
        return "warehouse/details";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('admin', 'whManager')")
    public String create(Model model) {

        model.addAttribute("warehouse", new Warehouses());
        return "warehouse/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('admin', 'whManager')")
    public String create(Model model, @ModelAttribute warehouseDAO warehouse) {
        Warehouses warehouses = whReponsitory.findByCode(warehouse.getCode());
        Warehouses warehousesName = whReponsitory.findByName(warehouse.getName());
        if (warehouses != null) {
//            model.addAttribute("location", locDAO);
            model.addAttribute("message", "Location Code have existed");
            return "redirect:warehouse/create";
        }
        if (warehousesName != null) {
//            model.addAttribute("location", locDAO);
            model.addAttribute("message", "Location Code have existed");
            return "redirect:warehouse/create";
        }
        service.saveWarehouse(warehouse);
        return "redirect:/warehouse/index";
    }

    @GetMapping("/update/{code}")
    @PreAuthorize("hasAnyRole('admin', 'whManager')")
    public String update(Model model, @PathVariable("code") String code) {
        Warehouses warehouse = whReponsitory.findByCode(code);
        model.addAttribute("warehouse", warehouse);
        return "warehouse/edit";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('admin', 'whManager')")
    public String update(Model model, @ModelAttribute warehouseDAO warehouses) {
        service.updateWarehouse(warehouses.getCode(), warehouses);
        return "redirect:/warehouse/index";
    }

    @GetMapping("/addLocation/{code}") // NHư trên này mới xài PathVariable
    @PreAuthorize("hasAnyRole('admin', 'whManager')")
    public String addLocation(Model model, @PathVariable("code") String code) {
        locationDAO locDAO = new locationDAO();
        model.addAttribute("location", locDAO);
        model.addAttribute("warehouse", service.findbycode(code));
        return "warehouse/addLocation";
    }

    //Không có xài PathVariable cho hàm post , trừ khi e add tham số vào url
    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('admin', 'whManager')")
    public String addLocation(Model model, String whCode, @ModelAttribute locationDAO locDAO, BindingResult bingdingResult) throws IOException {
        Warehouses warehouse = whReponsitory.findByCode(whCode);
        Locations loc = locReponsitory.findByCode(locDAO.getCode());
        Locations locName = locReponsitory.findByName(locDAO.getName());
        if (loc != null) {
//            model.addAttribute("location", locDAO);
            model.addAttribute("message", "Location Code have existed");
            return "redirect:/warehouse/addLocation/" + loc.getWarehouseCode().getCode();
        }
        if (locName != null) {
            model.addAttribute("errormessage", "Location Code have existed");
            return "redirect:/warehouse/addLocation/" + locName.getWarehouseCode().getCode();
        }
        Locations location = new Locations();
        location.setActive(locDAO.isActive());
        location.setCode(locDAO.getCode());
        location.setCapacity(locDAO.getCapacity());
        location.setName(locDAO.getName());
        location.setRemain(locDAO.getCapacity());
        location.setWarehouseCode(warehouse);
        locReponsitory.save(location);
        return "redirect:/warehouse/index";
    }
}
