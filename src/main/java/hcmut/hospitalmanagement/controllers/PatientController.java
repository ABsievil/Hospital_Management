package hcmut.hospitalmanagement.controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.services.PatientService;
import hcmut.hospitalmanagement.services.TreatmentHistoryService;

@RestController
@RequestMapping(path = "api/v1/Patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private TreatmentHistoryService treatmentHistoryService;

    // Get All Patients
    @GetMapping("/getAllPatient")
    public ResponseEntity<ResponseObject> getAllPatient() {
        return patientService.getAllPatient();
    }

    // Get Patient By ID
    @GetMapping("/getPatientById/{patientId}")
    public ResponseEntity<ResponseObject> getPatientById(@PathVariable Long patientId) {
        return patientService.getPatientById(patientId);
    }

    // Tìm bệnh nhân theo tên của họ
    @GetMapping("/getPatientByName/{name}")
    public ResponseEntity<ResponseObject> getPatientByName(@PathVariable String name) {
        return patientService.getPatientByName(name);
    }

    // Tìm hồ sơ bệnh án của bệnh nhân
    @GetMapping("/getTreatmentHistory/{patientId}")
    public ResponseEntity<ResponseObject> getTreatmentHistory(@PathVariable Long patientId) {
        return treatmentHistoryService.getTreatmentHistoryByPatientId(patientId);
    }

    // Thêm bệnh nhân
    @PostMapping("/insertPatient")
    public ResponseEntity<ResponseObject> insertPatient(@RequestBody Patient newPatient) {
        return patientService.insertPatient(newPatient);
    }
    
    // Chỉnh sửa thông tin bệnh nhân
    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<ResponseObject> updatePatientById(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return patientService.updatePatientById(id, updatedPatient);
    }


}
