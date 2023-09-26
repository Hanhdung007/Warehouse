/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package warehouse.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import warehouse.exam.demo.model.Customers;

/**
 *
 * @author LAPTOP123
 */
@Controller
@RequestMapping("/customers")
public class CustomersController {

    private final String url = "http://localhost:9999/api/customers/";
    private final RestTemplate rest;

    @Autowired
    public CustomersController(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('admin','sale')")
    public String listCustomers(Model model) {
        Customers[] customersArray = rest.getForObject(url, Customers[].class);
        model.addAttribute("customers", customersArray);
        return "customers/list";
    }

}
