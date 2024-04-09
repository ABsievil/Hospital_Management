package hcmut.hospitalmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.models.TreatmentHistory;
import hcmut.hospitalmanagement.services.TreatmentHistoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/TreatmentHistory")
public class TreatmentHistoryController {
    @Autowired
    TreatmentHistoryService treatmentHistoryService;

    // Tìm tất cả hồ sơ bệnh án
    @GetMapping("/getAllTreatmentHistory")
    public ResponseEntity<ResponseObject> getAllTreatmentHistory() {
        return treatmentHistoryService.getAllTreatmentHistory();
    }
    // Tìm hồ sơ bệnh án theo bệnh nhân
    @GetMapping("/getTreatmentHistoryByPatientId/{patientId}")
    public ResponseEntity<ResponseObject> getTreatmentHistoryByPatientId(@PathVariable Long patientId) {
        return treatmentHistoryService.getTreatmentHistoryByPatientId(patientId);
    }
    // Tìm hồ sơ bệnh án theo nhân viên y tế
    @GetMapping("/getTreatmentHistoryByEmployeeId/{employeeId}")
    public ResponseEntity<ResponseObject> getTreatmentHistoryByEmployeeId(@PathVariable Long employeeId) {
        return treatmentHistoryService.getTreatmentHistoryByEmployeeId(employeeId);
    }
    // Thêm hồ sơ bệnh án
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertTreatmentHistory(@RequestBody TreatmentHistory newTreatmentHistory) {
        return treatmentHistoryService.insertTreatmentHistory(newTreatmentHistory);
    }
    // Chỉnh sửa hồ sơ bệnh án
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateTreatmentHistory(@PathVariable Long id, 
    @RequestBody TreatmentHistory updatedTreatmentHistory) {
        return treatmentHistoryService.updateTreatmentHistory(id, updatedTreatmentHistory);
    }
    // xóa hồ sơ bệnh án
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteTreatmentHistory(@PathVariable Long id) {
        return treatmentHistoryService.deleteTreatmentHistory(id);
    }
    
}
