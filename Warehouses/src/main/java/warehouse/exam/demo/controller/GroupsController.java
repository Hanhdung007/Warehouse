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
import warehouse.exam.demo.model.Groups;

/**
 *
 * @author LAPTOP123
 */
@Controller
@RequestMapping("/groups")
public class GroupsController {

    private final String url = "http://localhost:9999/api/groups/";
    private final RestTemplate rest;

    @Autowired
    public GroupsController(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }

    @GetMapping("/list")
    public String getAllGroups(Model model) {
        Groups[] groups = rest.getForObject(url, Groups[].class);
        model.addAttribute("groups", groups);
        return "groups/list";
    }

}
