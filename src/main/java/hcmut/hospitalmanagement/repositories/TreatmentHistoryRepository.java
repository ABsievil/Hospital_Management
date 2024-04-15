package hcmut.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hcmut.hospitalmanagement.models.TreatmentHistory;

import java.time.LocalDate;
import java.util.List;


public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, Long> {
    List<TreatmentHistory> findByEmployeeId(Long employeeId);
    List<TreatmentHistory> findByPatientId(Long patientId);

    List<TreatmentHistory> findByAdmissionDateBetween(LocalDate date1, LocalDate date2);
}
