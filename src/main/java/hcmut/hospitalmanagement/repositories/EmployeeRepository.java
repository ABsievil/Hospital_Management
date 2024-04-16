package hcmut.hospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Employee findByUsername(String username);
    
    List<Employee> findByIsActive(boolean isActive);

    List<Employee> findByInformationFirstNameContainingIgnoreCase(String name);

    List<Employee> findByInformationOccupation(String occupation);

    List<Employee> findByInformationOccupationNotIn(List<String> occupations);

    Employee findByPersonalCode(String personalCode);

    
}
