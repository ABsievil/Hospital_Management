package hcmut.hospitalmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.models.Equipment;
import hcmut.hospitalmanagement.models.EquipmentType;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.repositories.EquipmentRepository;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    // Lấy tất cả thiết bị
    public ResponseEntity<ResponseObject> getAllEquipment() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Querry equipment successfully", equipmentList));
    }

    // Lấy thiết bị theo id
    public ResponseEntity<ResponseObject> getEquipmentById(Long id) {
        Equipment foundEquipment = equipmentRepository.findById(id).orElse(null);
        return foundEquipment != null
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Querry equipment successfully", foundEquipment))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("Failed", "Cannot find any equipment with id: " + id, null));
    }

    // Lấy thiết bị theo tên
    public ResponseEntity<ResponseObject> getEquipmentByName(String name) {
        Equipment foundEquipment = equipmentRepository.findByName(name);
        return foundEquipment != null
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Querry equipment successfully", foundEquipment))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("Failed", "Cannot find any equipment with name: " + name, null));
    }

    // Lấy thiết bị theo loại
    public ResponseEntity<ResponseObject> getEquipmentByType(EquipmentType type) {
        List<Equipment> equipmentList = equipmentRepository.findByType(type);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Query equipment successfully", equipmentList));
    }

    // Thêm thiết bị
    public ResponseEntity<ResponseObject> insertEquipment(Equipment newEquipment) {
        Equipment foundEquipment = equipmentRepository.findByName(newEquipment.getName());
        return foundEquipment == null
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Querry equipment successfully", equipmentRepository.save(newEquipment)))
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body(new ResponseObject("Failed", "Equipment name already exists", null));
    }

    // Chỉnh sửa thiết bị theo id
    public ResponseEntity<ResponseObject> updateEquipmentById(Long id, Equipment updatedEquipment) {
        Equipment foundEquipment = equipmentRepository.findById(id).orElse(null);
        // Kiểm tra thiết bị có tồn tại không
        if (foundEquipment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "Cannot find any equipment with id: " + id, null));
        }
        // Kiểm tra nếu thiết bị bị đổi tên thì có trùng với tên của các thiết bị khác trong database không
        if (!foundEquipment.getName().equals(updatedEquipment.getName())
                && equipmentRepository.findByName(updatedEquipment.getName()) != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResponseObject("Failed", "Equipment name already exists", null));
        }
        updatedEquipment.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Update equipment successfully", updatedEquipment));
    }

    // Chỉnh sửa thiết bị theo tên
    public ResponseEntity<ResponseObject> updateEquipmentByName(String name, Equipment updatedEquipment) {
        Equipment foundEquipment = equipmentRepository.findByName(name);
        // Kiểm tra thiết bị có tồn tại không
        if (foundEquipment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "Cannot find any equipment with name: " + name, null));
        }
        // Kiểm tra nếu thiết bị bị đổi tên thì có trùng với tên của các thiết bị khác trong database không
        if (!name.equals(updatedEquipment.getName())
                && equipmentRepository.findByName(updatedEquipment.getName()) != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResponseObject("Failed", "Equipment name already exists", null));
        }
        updatedEquipment.setId(foundEquipment.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Update equipment successfully", updatedEquipment));
    }

    // Xóa thiết bị theo id
    public ResponseEntity<ResponseObject> deleteEquipmentById(Long id) {
        Equipment foundEquipment = equipmentRepository.findById(id).orElse(null);
        // Kiểm tra thiết bị có tồn tại không
        if (foundEquipment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "Cannot find any equipment with id: " + id, null));
        }
        equipmentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Delete equipment successfully", null));
    }

    // Xóa thiết bị theo tên
    public ResponseEntity<ResponseObject> deleteEquipmentByName(String name) {
        Equipment foundEquipment = equipmentRepository.findByName(name);
        // Kiểm tra thiết bị có tồn tại không
        if (foundEquipment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "Cannot find any equipment with name: " + name, null));
        }
        equipmentRepository.deleteById(foundEquipment.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Delete equipment successfully", null));
    }
}
