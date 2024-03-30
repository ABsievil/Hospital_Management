package hcmut.hospitalmanagement.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.MedicineList;

public interface MedicineListRepository extends JpaRepository<MedicineList, Long> {
    List<MedicineList> findByDoctorId(Long doctorId);

    List<MedicineList> findByPatientId(Long patientId);
    
    List<MedicineList> findByDate(LocalDate date);

    List<MedicineList> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
