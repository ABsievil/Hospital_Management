package hcmut.hospitalmanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcmut.hospitalmanagement.models.Medicine;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.services.MedicineService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<Medicine> getAllMedicine() {
        return medicineService.getAllMedicine();
    }
    
    // Get Medicine by Id
    @GetMapping("/getMedicineById/{id}")
    public ResponseEntity<ResponseObject> getMedicineById(@PathVariable Long id) {
        Optional<Medicine> foundMedicine = medicineService.getMedicineById(id);
        return foundMedicine.isPresent()
            ? ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("OK", "Querry Medicine Successfully", foundMedicine.get()))
            : ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find Medicine with id: " + id, null));
    }

    // Get Medicine By Name
    @GetMapping("/getMedicineByName/{name}")
    public ResponseEntity<ResponseObject> getMedicineByName(@PathVariable String name) {
        Medicine foundMedicine = medicineService.getMedicineByName(name);
        return foundMedicine != null
            ? ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("OK", "Querry Medicine Successfully", foundMedicine))
            : ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find Medicine with name: " + name, null));
    }

    // Insert new Medicine
    @PostMapping("/insertMedicine")
    public ResponseEntity<ResponseObject> insertMedicine(@RequestBody Medicine newMedicine) {
        Medicine foundMedicine = medicineService.getMedicineByName(newMedicine.getName());
        return foundMedicine == null
            ? ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("OK", "Insert Medicine Successfully", medicineService.saveMedicine(newMedicine)))
            : ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Medicine name already exists " , null));
    }
    
    // Update Medicine By Id
    @PutMapping("/updateMedicineById/{id}")
    public ResponseEntity<ResponseObject> updateMedicineById(@PathVariable Long id, @RequestBody Medicine updatedMedicine) {
        ResponseObject responseObject = medicineService.updateMedicineById(id, updatedMedicine);
        return responseObject.getData() != null
            ? ResponseEntity.status(HttpStatus.OK).body(responseObject)
            : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(responseObject);
    }

    // Update Medicine by Name
    @PutMapping("/updateMedicineByName/{name}")
    public ResponseEntity<ResponseObject> updateMedicineByName(@PathVariable String name, @RequestBody Medicine updatedMedicine) {
        ResponseObject responseObject = medicineService.updateMedicineByName(name, updatedMedicine);
        return responseObject.getData() != null
            ? ResponseEntity.status(HttpStatus.OK).body(responseObject)
            : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(responseObject);
    }

    // Delete Medicine by Id
    @DeleteMapping("/deleteMedicineById/{id}")
    public ResponseEntity<ResponseObject> deleteMedicineById(@PathVariable Long id) {
        if (!medicineService.getMedicineById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find Medicine with id: " + id, null));
        }
        medicineService.deleteMedicineById(id);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Delete Medicine Successfully with id: " + id, null));
    }

    // Delete Medicine By Name
    @DeleteMapping("/deleteMedicineByName/{name}")
    public ResponseEntity<ResponseObject> deleteMedicineByName(@PathVariable String name) {
        if (medicineService.getMedicineByName(name) == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find Medicine with name: " + name, null));
        }
        medicineService.deleteMedicineByName(name);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Delete Medicine Successfully with name: " + name, null));
    }
}
