package hcmut.hospitalmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.models.Employee;
import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.models.Rate;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.models.TreatmentHistory;
import hcmut.hospitalmanagement.repositories.EmployeeRepository;
import hcmut.hospitalmanagement.repositories.PatientRepository;
import hcmut.hospitalmanagement.repositories.TreatmentHistoryRepository;

@Service
public class TreatmentHistoryService {
    @Autowired
    private TreatmentHistoryRepository treatmentHistoryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PatientRepository patientRepository;

    // tìm tất cả hồ sơ bệnh án
    public ResponseEntity<ResponseObject> getAllTreatmentHistory() {
        List<TreatmentHistory> treatmentHistoryList = treatmentHistoryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Query treatment history successfully", treatmentHistoryList));
    }

    // tìm hồ sơ bệnh án theo id bệnh nhân
    public ResponseEntity<ResponseObject> getTreatmentHistoryByPatientId(Long patientId) {
        List<TreatmentHistory> treatmentHistoryList = treatmentHistoryRepository.findByPatientId(patientId);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Query treatment history successfully", treatmentHistoryList));
    }
    
    // Tìm hồ sơ bệnh án theo id bác sĩ
    public ResponseEntity<ResponseObject> getTreatmentHistoryByEmployeeId(Long employeeId) {
        List<TreatmentHistory> treatmentHistoryList = treatmentHistoryRepository.findByEmployeeId(employeeId);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Query treatment history successfully", treatmentHistoryList));
    }

    // thêm hồ sơ bệnh án
    public ResponseEntity<ResponseObject> insertTreatmentHistory(TreatmentHistory newTH) {
        // Kiểm tra nhân viên y tế
        if (newTH.getEmployeeId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Employee id cannot be null", null));
        }
        Employee foundEmployee = employeeRepository.findById(newTH.getEmployeeId()).orElse(null);
        if (foundEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Employee id does not exists", null));
        }
        // Kiểm tra bệnh nhân
        if (newTH.getPatientId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Patient id cannot be null", null));
        }
        Patient foundPatient = patientRepository.findById(newTH.getPatientId()).orElse(null);
        if (foundPatient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Patient id does not exists", null));
        }
        // Kiểm tra ngày bắt đầu khám
        if (newTH.getAdmissionDate() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Admission date cannot be null", null));
        }
        // Kiểm tra ngày kết thúc khám
        if (newTH.getDischargeDate() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Discharge date cannot be null", null));
        }
        // Kiểm tra bệnh
        if (newTH.getDisease() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Disease cannot be null", null));
        }
        // Kiểm tra phí
        if (newTH.getCost() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Cost must be positive", null));
        }
        // Kiểm tra đơn thuốc
        if (newTH.getMedicationList() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Medication list cannot be null", null));
        }
        // Kiểm tra độ hài lòng
        if (newTH.getRate() == null) {
            newTH.setRate(Rate.VERYGOOD);
        }
        newTH.setEmployeeFirstName(foundEmployee.getInformation().getFirstName());
        newTH.setEmployeeLastName(foundEmployee.getInformation().getLastName());
        newTH.setPatientFirstName(foundPatient.getInformation().getFirstName());
        newTH.setPatientLastName(foundPatient.getInformation().getLastName());
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Insert treatment history successfully", treatmentHistoryRepository.save(newTH)));
    }

    // chỉnh sửa hồ sơ bệnh án
    public ResponseEntity<ResponseObject> updateTreatmentHistory(Long id, TreatmentHistory updatedTH) {
        // Kiểm tra id của hồ sơ bệnh án
        if (!treatmentHistoryRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Treatment history id does not exists", null));
        }
        // Kiểm tra nhân viên y tế
        if (updatedTH.getEmployeeId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Employee id cannot be null", null));
        }
        Employee foundEmployee = employeeRepository.findById(updatedTH.getEmployeeId()).orElse(null);
        if (foundEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Employee id does not exists", null));
        }
        // Kiểm tra bệnh nhân
        if (updatedTH.getPatientId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Patient id cannot be null", null));
        }
        Patient foundPatient = patientRepository.findById(updatedTH.getPatientId()).orElse(null);
        if (foundPatient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Patient id does not exists", null));
        }
        // Kiểm tra ngày bắt đầu khám
        if (updatedTH.getAdmissionDate() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Admission date cannot be null", null));
        }
        // Kiểm tra ngày kết thúc khám
        if (updatedTH.getDischargeDate() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Discharge date cannot be null", null));
        }
        // Kiểm tra bệnh
        if (updatedTH.getDisease() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Disease cannot be null", null));
        }
        // Kiểm tra phí
        if (updatedTH.getCost() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Cost must be positive", null));
        }
        // Kiểm tra đơn thuốc
        if (updatedTH.getMedicationList() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Medication list cannot be null", null));
        }
        // Kiểm tra độ hài lòng
        if (updatedTH.getRate() == null) {
            updatedTH.setRate(Rate.VERYGOOD);
        }
        updatedTH.setEmployeeFirstName(foundEmployee.getInformation().getFirstName());
        updatedTH.setEmployeeLastName(foundEmployee.getInformation().getLastName());
        updatedTH.setPatientFirstName(foundPatient.getInformation().getFirstName());
        updatedTH.setPatientLastName(foundPatient.getInformation().getLastName());
        updatedTH.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Update treatment history successfully", treatmentHistoryRepository.save(updatedTH)));
    }

    // Xóa hồ sơ bệnh án theo id
    public ResponseEntity<ResponseObject> deleteTreatmentHistory(Long id) {
        // Kiểm tra id của hồ sơ bệnh án
        if (!treatmentHistoryRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Treatment history id does not exists", null));
        }
        treatmentHistoryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Delete treatment history successfully", null));
    }
}
