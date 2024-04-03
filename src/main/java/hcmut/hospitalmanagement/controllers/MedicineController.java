package hcmut.hospitalmanagement.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcmut.hospitalmanagement.models.Medicine;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.services.MedicineService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/v1/Medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    // Get all Medicines
    @GetMapping("/getAllMedicine")
    public ResponseEntity<ResponseObject> getAllMedicine() {
        return medicineService.getAllMedicine();
    }
    
    // Get Medicine by Id
    @GetMapping("/getMedicineById/{id}")
    public ResponseEntity<ResponseObject> getMedicineById(@PathVariable Long id) {
        return medicineService.getMedicineById(id);
    }

    // Get Medicine By Name
    @GetMapping("/getMedicineByName/{name}")
    public ResponseEntity<ResponseObject> getMedicineByName(@PathVariable String name) {
        return medicineService.getMedicineByName(name);
    }

    // Insert new Medicine
    @PostMapping("/insertMedicine")
    public ResponseEntity<ResponseObject> insertMedicine(@RequestBody Medicine newMedicine) {
        return medicineService.insertMedicine(newMedicine);
    }
    
    // Update Medicine By Id
    @PutMapping("/updateMedicineById/{id}")
    public ResponseEntity<ResponseObject> updateMedicineById(@PathVariable Long id, @RequestBody Medicine updatedMedicine) {
        return medicineService.updateMedicineById(id, updatedMedicine);
    }

    // Update Medicine by Name
    @PutMapping("/updateMedicineByName/{name}")
    public ResponseEntity<ResponseObject> updateMedicineByName(@PathVariable String name, @RequestBody Medicine updatedMedicine) {
        return medicineService.updateMedicineByName(name, updatedMedicine);
    }

    // Delete Medicine by Id
    @DeleteMapping("/deleteMedicineById/{id}")
    public ResponseEntity<ResponseObject> deleteMedicineById(@PathVariable Long id) {
        return medicineService.deleteMedicineById(id);
    }

    // Delete Medicine By Name
    @DeleteMapping("/deleteMedicineByName/{name}")
    public ResponseEntity<ResponseObject> deleteMedicineByName(@PathVariable String name) {
        return medicineService.deleteMedicineByName(name);
    }
}
