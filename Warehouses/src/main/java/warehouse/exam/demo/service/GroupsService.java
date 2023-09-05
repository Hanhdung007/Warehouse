/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.model.Groups;
import warehouse.exam.demo.reponsitory.GroupsRepository;

/**
 *
 * @author LAPTOP123
 */
@Service
public class GroupsService {

    private final GroupsRepository groupsRepository;

    @Autowired
    public GroupsService(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    public List<Groups> getAllGroups() {
        return (List<Groups>) groupsRepository.findAll();
    }

    public Optional<Groups> getGroupsByID(Integer groupID) {
        return groupsRepository.findById(groupID);
    }

    public Groups saveGroups(Groups groups) {
        return groupsRepository.save(groups);
    }

    public void deleteGroups(Integer groupID) {
        groupsRepository.deleteById(groupID);
    }
}
