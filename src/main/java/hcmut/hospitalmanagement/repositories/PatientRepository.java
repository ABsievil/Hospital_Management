package hcmut.hospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByPatientName(String patientName);
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
}
>>>>>>> a491d596bf33b1a0661a2754459147cf5674dd2c
=======
}
>>>>>>> 66f2c27ec1606a90a7bbf0ae73c377c5acd60c88
