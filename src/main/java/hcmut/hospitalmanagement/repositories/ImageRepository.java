package hcmut.hospitalmanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.ImageData;

public interface ImageRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String fileName);
    
}
