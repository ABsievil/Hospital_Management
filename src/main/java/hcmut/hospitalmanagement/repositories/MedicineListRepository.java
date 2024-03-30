package hcmut.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.MedicineList;

public interface MedicineListRepository extends JpaRepository<MedicineList, Long> {

}
