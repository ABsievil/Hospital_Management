package hcmut.hospitalmanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // Insert New Patient
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Patient newPatient) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product Successfully", repository.save(newPatient)));
    }

    // Update Patient by ID, If Patient does not exists then Insert New Patient
    // @PutMapping("/{id}")
    // ResponseEntity<ResponseObject> updateProduct(@RequestBody Patient newPatient, @PathVariable Long id) {
    //     Patient updatedPatient = repository.findById(id).map(patient -> {
    //         patient.setPatientName(newPatient.getPatientName());
    //         patient.setMedicalHistory(newPatient.getMedicalHistory());
    //         patient.setTestResults(newPatient.getTestResults());
    //         patient.setTreatmentSchedule(newPatient.getTreatmentSchedule());
    //         return repository.save(patient);
    //     }).orElseGet(() -> {
    //         newPatient.setId(id);
    //         return repository.save(newPatient);
    //     });
    //     return ResponseEntity.status(HttpStatus.OK).body(
    //             new ResponseObject("ok", "Update patient with id = " + updatedPatient.getId() + " successfully",
    //                     updatedPatient));
    // }

    // Delete Patient if exists
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete patient with id = " + id + " successfull", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find patient with id = " + id, ""));
    }
}
