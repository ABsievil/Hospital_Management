package hcmut.hospitalmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.models.Employee;
import hcmut.hospitalmanagement.repositories.EmployeeRepository;
import hcmut.hospitalmanagement.security.CustomUserDetails;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Check if the user exists in the database?
        Employee employee = employeeRepository.findByUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(employee);
    }
}
