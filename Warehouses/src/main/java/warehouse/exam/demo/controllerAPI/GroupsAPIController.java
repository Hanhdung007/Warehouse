/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package warehouse.exam.demo.controllerAPI;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.model.Groups;
import warehouse.exam.demo.service.GroupsService;

/**
 *
 * @author LAPTOP123
 */
@RestController
@RequestMapping("/api/groups")
public class GroupsAPIController {

    private final GroupsService groupsService;

    @Autowired
    public GroupsAPIController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping
    public ResponseEntity<List<Groups>> getAllGroups() {
        List<Groups> groupsList = groupsService.getAllGroups();
        return new ResponseEntity<>(groupsList, HttpStatus.OK);
    }

    @GetMapping("/{groupID}")
    public ResponseEntity<Groups> getGroupsByID(@PathVariable Integer groupID) {
        Optional<Groups> groups = groupsService.getGroupsByID(groupID);
        return groups.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Groups> saveGroups(@RequestBody Groups groups) {
        Groups savedGroups = groupsService.saveGroups(groups);
        return new ResponseEntity<>(savedGroups, HttpStatus.CREATED);
    }

    @DeleteMapping("/{groupID}")
    public ResponseEntity<Void> deleteGroups(@PathVariable Integer groupID) {
        groupsService.deleteGroups(groupID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
