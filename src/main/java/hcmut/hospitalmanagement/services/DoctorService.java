package hcmut.hospitalmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.repositories.DoctorRepository;

@Service
public class DoctorService implements DService {
    private final DoctorRepository doctorRepository;

    // Contructors
    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Implementations of Methods in DService
    
}
