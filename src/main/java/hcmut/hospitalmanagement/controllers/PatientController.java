package hcmut.hospitalmanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.repositories.PatientRepository;

@RestController
@RequestMapping(path = "api/v1/Patients")
public class PatientController {

    // @Autowired is used to connect to class PatientRepository 
    @Autowired
    private PatientRepository repository;


    // Get All Patients 
    @GetMapping("")
    List<Patient> getAllPatients() {
        return repository.findAll();
    }

    // Get Patient By ID
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findByID(@PathVariable Long id) {
        Optional<Patient> foundPatient = repository.findById(id);
        return foundPatient.isPresent()
                ? ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok", "Query Patient Successfully", foundPatient))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject("failed", "Cannot find Patient with id = " + id, ""));
    }
    
}
