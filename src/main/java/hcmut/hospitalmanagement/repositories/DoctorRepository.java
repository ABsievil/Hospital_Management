package hcmut.hospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByUsername(String username);
    
    List<Doctor> findByIsActive(boolean working);

    List<Doctor> findByInformationNameContainingIgnoreCase(String name);

    List<Doctor> findByInformationOccupation(String occupation);

    List<Doctor> findByInformationOccupationNotIn(List<String> occupations);
}
