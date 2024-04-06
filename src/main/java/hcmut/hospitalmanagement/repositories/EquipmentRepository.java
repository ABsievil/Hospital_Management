package hcmut.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import hcmut.hospitalmanagement.models.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findByName(String name);
}
