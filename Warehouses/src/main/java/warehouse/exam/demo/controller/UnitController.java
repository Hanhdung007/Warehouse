/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import warehouse.exam.demo.model.Unit;

/**
 *
 * @author LAPTOP123
 */
@Controller
@RequestMapping("/units")
public class UnitController {

    private final String url = "http://localhost:9999/api/units/";
    private final RestTemplate rest;

    @Autowired
    public UnitController(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }

    @GetMapping("/list")
    public String getAllUnit(Model model) {
        Unit[] units = rest.getForObject(url, Unit[].class);
        model.addAttribute("units", units);
        return "unit/list";
    }

}
