/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.DAL.locationDAO;
import warehouse.exam.demo.model.Locations;
import warehouse.exam.demo.service.locationService;
import warehouse.exam.demo.service.warehouseService;

/**
 *
 * @author DUNG
 */
@Controller
@RequestMapping("/location")
public class locationController {

    @Autowired
    locationService locService;
    @Autowired
    warehouseService whService;

    @RequestMapping("/index")
    @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
    public String index(Model model) {
        model.addAttribute("list", locService.getAll());
        return "location/index";
    }

    @RequestMapping("/details/{id}")
    @PreAuthorize("hasAnyRole('admin', 'sale', 'importer', 'whManager', 'qc')")
    public String details(Model model, @PathVariable("id") String id) {
        model.addAttribute("model", locService.findByLocationCode(id));
        return "location/details";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('whManager')")
    public String create(Model model) {
        model.addAttribute("location", new locationDAO());
        model.addAttribute("warehouse", whService.getAll());
        return "location/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('whManager')")
    public String create(Model model, @ModelAttribute locationDAO location) {
        model.addAttribute("warehouse", whService.getAll());
        locService.saveLocation(location);
        return "redirect:/location/index";
    }

    @GetMapping("/update/{code}")
    @PreAuthorize("hasRole('whManager')")
    public String update(Model model, @PathVariable("code") String code) {
        Locations location = locService.findOne(code);
        model.addAttribute("location", location);
        model.addAttribute("warehouse", whService.getAll());
        return "location/edit";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('whManager')")
    public String update(Model model, @ModelAttribute locationDAO location) {
        model.addAttribute("location", locService.findOne(location.getCode()));
        model.addAttribute("warehouse", whService.getAll());
        locService.saveLocation(location);
        return "redirect:/location/index";
    }
}
