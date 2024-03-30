package hcmut.hospitalmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.models.Medicine;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.repositories.MedicineRepository;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getAllMedicine() {
        return medicineRepository.findAll();
    }

    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    public Medicine getMedicineByName(String name) {
        return medicineRepository.findByName(name);
    }

    public ResponseObject updateMedicineById(Long id, Medicine updatedMedicine) {
        Medicine foundMedicine = medicineRepository.findById(id).orElse(null);
        if (foundMedicine == null) {
            return new ResponseObject("Failed", "Cannot find Medicine with id: " + id, null);
        }
        if (!foundMedicine.getName().equals(updatedMedicine.getName()) &&
                medicineRepository.findByName(updatedMedicine.getName()) != null) {
            return new ResponseObject("Failed", "Medicine name already exists", null);
        }
        updatedMedicine.setId(id);
        return new ResponseObject("OK", "Update Medicine Successfully with id: " + id,
                medicineRepository.save(updatedMedicine));
    }

    public ResponseObject updateMedicineByName(String name, Medicine updatedMedicine) {
        Medicine existingMedicine = medicineRepository.findByName(name);
        if (existingMedicine == null) {
            return new ResponseObject("Failed", "Cannot find Medicine with name: " + name, null);
        }
        if (!existingMedicine.getName().equals(updatedMedicine.getName()) && 
        medicineRepository.findByName(updatedMedicine.getName()) != null) {
            return new ResponseObject("Failed", "Medicine name already exists", null);
        }
        updatedMedicine.setId(existingMedicine.getId());
        return new ResponseObject("OK", "Update Medicine Successfully with name: " + name,
                medicineRepository.save(updatedMedicine));
    }

    public void deleteMedicineById(Long id) {
        medicineRepository.deleteById(id);
    }

    public void deleteMedicineByName(String name) {
        Medicine medicine = medicineRepository.findByName(name);
        if (medicine != null) {
            medicineRepository.delete(medicine);
        }
    }
}
