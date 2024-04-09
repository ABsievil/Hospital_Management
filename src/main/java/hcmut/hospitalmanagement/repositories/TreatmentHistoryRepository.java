package hcmut.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.TreatmentHistory;
import java.util.List;


public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, Long> {
    List<TreatmentHistory> findByEmployeeId(Long employeeId);
    List<TreatmentHistory> findByPatientId(Long patientId);
}
