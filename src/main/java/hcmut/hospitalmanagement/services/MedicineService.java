package hcmut.hospitalmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.models.Medicine;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.repositories.MedicineRepository;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    
    // Lấy tất cả thuốc
    public ResponseEntity<ResponseObject> getAllMedicine() {
        List<Medicine> medicineList = medicineRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Querry medicine successfully", medicineList));
    }

    // Lấy thuốc theo id
    public ResponseEntity<ResponseObject> getMedicineById(Long id) {
        Medicine foundMedicine = medicineRepository.findById(id).orElse(null);
        return foundMedicine != null
        ? ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Querry medicine successfully", foundMedicine))
        : ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ResponseObject("Failed", "Cannot find any medicine with id: " + id, null));
    }

    // Lấy thuốc theo tên
    public ResponseEntity<ResponseObject> getMedicineByName(String name) {
        Medicine foundMedicine = medicineRepository.findByName(name);
        return foundMedicine != null
        ? ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Querry medicine successfully", foundMedicine))
        : ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ResponseObject("Failed", "Cannot find any medicine with name: " + name, null));
    }

    // Thêm thuốc
    public ResponseEntity<ResponseObject> insertMedicine(Medicine newMedicine) {
        Medicine foundMedicine = medicineRepository.findByName(newMedicine.getName());
        return foundMedicine == null
        ? ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Querry medicine successfully", medicineRepository.save(newMedicine)))
        : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
        .body(new ResponseObject("Failed", "Medicine name already exists", null));
    }

    // Chỉnh sửa thuốc theo id
    public ResponseEntity<ResponseObject> updateMedicineById(Long id, Medicine updatedMedicine) {
        Medicine foundMedicine = medicineRepository.findById(id).orElse(null);
        // Kiểm tra thuốc có tồn tại không
        if (foundMedicine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any medicine with id: " + id, null));
        }
        // Kiểm tra nếu thuốc bị đổi tên thì có trùng với tên của các thuốc khác trong database không
        if (foundMedicine.getName() != updatedMedicine.getName() && 
        medicineRepository.findByName(updatedMedicine.getName()) != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Medicine name already exists", null));
        }
        updatedMedicine.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Update medicine successfully", updatedMedicine));
    }

    // Chỉnh sửa thuốc theo tên
    public ResponseEntity<ResponseObject> updateMedicineByName(String name, Medicine updatedMedicine) {
        Medicine foundMedicine = medicineRepository.findByName(name);
        // Kiểm tra thuốc có tồn tại không
        if (foundMedicine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any medicine with name: " + name, null));
        }
        // Kiểm tra nếu thuốc bị đổi tên thì có trùng với tên của các thuốc khác trong database không
        if (name != updatedMedicine.getName() && medicineRepository.findByName(updatedMedicine.getName()) != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Medicine name already exists", null));
        }
        updatedMedicine.setId(foundMedicine.getId());
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Update medicine successfully", updatedMedicine));
    }

    // Xóa thuốc theo id
    public ResponseEntity<ResponseObject> deleteMedicineById(Long id) {
        Medicine foundMedicine = medicineRepository.findById(id).orElse(null);
        // Kiểm tra thuốc có tồn tại không
        if (foundMedicine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any medicine with id: " + id, null));
        }
        medicineRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Delete medicine successfully", null));
    }

    // Xóa thuốc theo tên
    public ResponseEntity<ResponseObject> deleteMedicineByName(String name) {
        Medicine foundMedicine = medicineRepository.findByName(name);
        // Kiểm tra thuốc có tồn tại không
        if (foundMedicine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any medicine with name: " + name, null));
        }
        medicineRepository.deleteById(foundMedicine.getId());
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Delete medicine successfully", null));
    }
}