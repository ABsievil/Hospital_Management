package hcmut.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
