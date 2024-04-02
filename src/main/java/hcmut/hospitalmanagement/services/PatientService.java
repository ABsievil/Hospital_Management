package hcmut.hospitalmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// import hcmut.hospitalmanagement.models.Employee;
import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.repositories.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    // Tìm tất cả bệnh nhân
    public ResponseEntity<ResponseObject> getAllPatient() {
        List<Patient> employeeList = patientRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Querry patient successfully", employeeList));
    }

    // Tìm bệnh nhân theo id
    public ResponseEntity<ResponseObject> getPatientById(Long patientId) {
        Patient foundPatient = patientRepository.findById(patientId).orElse(null);
        return foundPatient != null
            ? ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("OK", "Query patient successfully", foundPatient))
            : ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any patient with id: " + patientId, null));
    }

    // Tìm bệnh nhân theo tên
    public ResponseEntity<ResponseObject> getPatientByName(String name) {
        List<Patient> patientList = patientRepository.findByInformationNameContainingIgnoreCase(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Querry patient successfully", patientList));
    }

    // Thêm bệnh nhân 
    public ResponseEntity<ResponseObject> insertPatient(Patient newPatient) {
        if (newPatient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "New patient object is null", null));
        }
    
        // Tiếp tục với quá trình thêm mới nếu newPatient không null
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Insert patient successfully", patientRepository.save(newPatient)));
    }
    

    // Chỉnh sửa thông tin bệnh nhân
    public ResponseEntity<ResponseObject> updatePatientById(Long id, Patient updatedPatient) {
        Patient foundPatient = patientRepository.findById(id).orElse(null);
        // Kiểm tra id của bệnh nhân có tồn tại không
        if (foundPatient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any employee with id: " + id, null));
        }

        updatedPatient.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Update employee successfully", patientRepository.save(updatedPatient)));
    }
}
