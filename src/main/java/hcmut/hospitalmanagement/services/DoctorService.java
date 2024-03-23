package hcmut.hospitalmanagement.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hcmut.hospitalmanagement.models.Doctor;
import hcmut.hospitalmanagement.models.ImageData;
import hcmut.hospitalmanagement.repositories.DoctorRepository;
import hcmut.hospitalmanagement.repositories.ImageRepository;

@Service
public class DoctorService {
    @Autowired
    private final DoctorRepository doctorRepository;
    @Autowired
    private ImageRepository imageRepository;

    // Contructors
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Implementations of Methods in DService
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getActiveDoctors() {
        return doctorRepository.findByActiveTrue();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    @Transactional
    public void setDoctorImage(Long doctorId, Long imageDataId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        ImageData imageData = imageRepository.findById(imageDataId).orElse(null);

        if (doctor != null && imageData != null) {
            doctor.setImage(imageData);
            imageData.setDoctor(doctor);

            doctorRepository.save(doctor);
            imageRepository.save(imageData);
        } else {
            // Handle the case where either doctor or imageData is not found
            throw new IllegalArgumentException("Doctor or ImageData not found");
        }
    }
    

    @Transactional(readOnly = true)
    public ImageData getDoctorImage(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor != null) {
            return doctor.getImage();
        } else {
            return null;
        }
    }
}