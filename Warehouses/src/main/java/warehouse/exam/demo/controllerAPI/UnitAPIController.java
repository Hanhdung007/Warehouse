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
import warehouse.exam.demo.model.Unit;
import warehouse.exam.demo.service.UnitService;

/**
 *
 * @author LAPTOP123
 */
@RestController
@RequestMapping("/api/units")
public class UnitAPIController {

    private final UnitService unitService;

    @Autowired
    public UnitAPIController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<List<Unit>> getAllUnits() {
        List<Unit> unitList = unitService.getAllUnit();
        return new ResponseEntity<>(unitList, HttpStatus.OK);
    }

    @GetMapping("/{unitID}")
    public ResponseEntity<Unit> getUnitByID(@PathVariable Integer unitID) {
        Optional<Unit> unit = unitService.getUnitByID(unitID);
        return unit.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Unit> saveUnit(@RequestBody Unit unit) {
        Unit savedUnit = unitService.saveUnit(unit);
        return new ResponseEntity<>(savedUnit, HttpStatus.CREATED);
    }

    @DeleteMapping("/{unitID}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Integer unitID) {
        unitService.deleteUnit(unitID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
