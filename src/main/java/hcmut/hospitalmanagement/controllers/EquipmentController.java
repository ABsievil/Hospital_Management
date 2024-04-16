package hcmut.hospitalmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hcmut.hospitalmanagement.models.Equipment;
import hcmut.hospitalmanagement.models.EquipmentType;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.services.EquipmentService;

@RestController
@RequestMapping("api/v1/Equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    // Get all Equipments
    @GetMapping("/getAllEquipment")
    public ResponseEntity<ResponseObject> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }
    
    // Get Equipment by Id
    @GetMapping("/getEquipmentById/{id}")
    public ResponseEntity<ResponseObject> getEquipmentById(@PathVariable Long id) {
        return equipmentService.getEquipmentById(id);
    }

    // Get Equipment By Name
    @GetMapping("/getEquipmentByName/{name}")
    public ResponseEntity<ResponseObject> getEquipmentByName(@PathVariable String name) {
        return equipmentService.getEquipmentByName(name);
    }

    // Get equipment by type == BIG
    @GetMapping("/getBigEquipment")
    public ResponseEntity<ResponseObject> getBigEquipment() {
        return equipmentService.getEquipmentByType(EquipmentType.BIG);
    }

    // Get equipment by ype == SMALL
    @GetMapping("/getSmallEquipment")
    public ResponseEntity<ResponseObject> getSmallEquipment() {
        return equipmentService.getEquipmentByType(EquipmentType.SMALL);
    }

    // Insert new Equipment
    @PostMapping("/insertEquipment")
    public ResponseEntity<ResponseObject> insertEquipment(@RequestBody Equipment newEquipment) {
        return equipmentService.insertEquipment(newEquipment);
    }
    
    // Update Equipment By Id
    @PutMapping("/updateEquipmentById/{id}")
    public ResponseEntity<ResponseObject> updateEquipmentById(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        return equipmentService.updateEquipmentById(id, updatedEquipment);
    }

    // Update Equipment by Name
    @PutMapping("/updateEquipmentByName/{name}")
    public ResponseEntity<ResponseObject> updateEquipmentByName(@PathVariable String name, @RequestBody Equipment updatedEquipment) {
        return equipmentService.updateEquipmentByName(name, updatedEquipment);
    }

    // Delete Equipment by Id
    @DeleteMapping("/deleteEquipmentById/{id}")
    public ResponseEntity<ResponseObject> deleteEquipmentById(@PathVariable Long id) {
        return equipmentService.deleteEquipmentById(id);
    }

    // Delete Equipment By Name
    @DeleteMapping("/deleteEquipmentByName/{name}")
    public ResponseEntity<ResponseObject> deleteEquipmentByName(@PathVariable String name) {
        return equipmentService.deleteEquipmentByName(name);
    }
}
