package hcmut.hospitalmanagement.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByName(String name);
}
