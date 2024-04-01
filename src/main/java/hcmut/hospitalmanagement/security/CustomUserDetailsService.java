package hcmut.hospitalmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import hcmut.hospitalmanagement.models.Patient;

import hcmut.hospitalmanagement.repositories.PatientRepository;
import hcmut.hospitalmanagement.security.CustomUserDetails;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Check if the user exists in the database?
        Patient patient = patientRepository.findByUsername(username);
        if (patient == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(patient);
    }
}
