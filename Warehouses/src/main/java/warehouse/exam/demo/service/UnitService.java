/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package warehouse.exam.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exam.demo.model.Unit;
import warehouse.exam.demo.reponsitory.UnitRepository;

/**
 *
 * @author LAPTOP123
 */
@Service
public class UnitService {

    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getAllUnit() {
        return unitRepository.findAll();
    }

    public Optional<Unit> getUnitByID(Integer unitID) {
        return unitRepository.findById(unitID);
    }

    public Unit saveUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    public void deleteUnit(Integer unitID) {
        unitRepository.deleteById(unitID);
    }
}
