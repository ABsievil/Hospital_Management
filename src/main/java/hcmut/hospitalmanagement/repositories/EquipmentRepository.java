package hcmut.hospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import hcmut.hospitalmanagement.models.Equipment;
import hcmut.hospitalmanagement.models.EquipmentType;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findByName(String name);

    List<Equipment> findByType(EquipmentType type);
}
