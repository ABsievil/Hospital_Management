package hcmut.hospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // List<Patient> findByPatientName(String patientName);
}
