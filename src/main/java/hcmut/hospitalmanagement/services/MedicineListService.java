package hcmut.hospitalmanagement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.models.MedicineList;
import hcmut.hospitalmanagement.repositories.MedicineListRepository;

@Service
public class MedicineListService {
    @Autowired
    private MedicineListRepository medicineListRepository;

    public MedicineList saveMedicineList(MedicineList medicineList) {
        return medicineListRepository.save(medicineList);
    }

    public Optional<MedicineList> getMedicineById(Long id) {
        return medicineListRepository.findById(id);
    }

    public List<MedicineList> getAllMedicineList() {
        return medicineListRepository.findAll();
    }
    
    public List<MedicineList> getMedineListByDoctorId(Long doctorId) {
        return medicineListRepository.findByDoctorId(doctorId);
    }

    public List<MedicineList> getMedicineListByPatientId(Long patientId) {
        return medicineListRepository.findByPatientId(patientId);
    } 

    public List<MedicineList> getMedicineListByDate(LocalDate date) {
        return medicineListRepository.findByDate(date);
    }

    public List<MedicineList> getMedicineListBetweenDate(LocalDate startDate, LocalDate endDate) {
        return medicineListRepository.findByDateBetween(startDate, endDate);
    }

    public void deleteMedicineListById(Long id) {
        medicineListRepository.deleteById(id);
    }
}
