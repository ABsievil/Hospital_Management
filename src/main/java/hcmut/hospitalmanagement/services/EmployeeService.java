package hcmut.hospitalmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.models.Employee;
import hcmut.hospitalmanagement.models.PwdDTO;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.models.Role;
import hcmut.hospitalmanagement.models.UsernameDTO;
import hcmut.hospitalmanagement.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Tìm tất cả Nhân viên y tế
    public ResponseEntity<ResponseObject> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Querry employee successfully", employeeList));
    }

    // Tìm tất cả nhân viên y tế vẫn đang công tác tại bệnh viện
    public ResponseEntity<ResponseObject> getActiveEmployee() {
        List<Employee> employeeList = employeeRepository.findByIsActive(true);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Querry employee successfully", employeeList));
    }

    // Tìm tất cả nhân viên y tế đã nghỉ việc
    public ResponseEntity<ResponseObject> getNonActiveEmployee() {
        List<Employee> employeeList = employeeRepository.findByIsActive(false);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Querry employee successfully", employeeList));
    }

    // Tìm nhân viên y tế theo id của họ
    public ResponseEntity<ResponseObject> getEmployeeById(Long employeeId) {
        Employee foundEmployee = employeeRepository.findById(employeeId).orElse(null);
        return foundEmployee != null
            ? ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("OK", "Query employee successfully", foundEmployee))
            : ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any employee with id: " + employeeId, null));
    }

    // Tìm employee theo username
    public ResponseEntity<ResponseObject> getEmployeeByUsername(String username) {
        Employee foundEmployee = employeeRepository.findByUsername(username);
        return foundEmployee != null
            ? ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("OK", "Query employee successfully", foundEmployee))
            : ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any employee with username: " + username, null));
    }

    // Tìm nhân viên y tế theo tên của họ
    public ResponseEntity<ResponseObject> getEmployeeByName(String name) {
        List<Employee> employeeList = employeeRepository.findByInformationFirstNameContainingIgnoreCase(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Querry employee successfully", employeeList));
    }

    // Tìm nhân viên y tế theo nghề nghiệp cụ thể (ví dụ: Doctor, Nurse, ...)
    public ResponseEntity<ResponseObject> getEmployeeByRole(List<Role> roles) {
        List<Employee> employeeList = employeeRepository.findByRoleIn(roles);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Querry employee successfully", employeeList));
    }


    // Thêm employee 
    public ResponseEntity<ResponseObject> insertEmployee(Employee newEmployee) {
        // Kiểm tra xem thông in có username chưa
        if (newEmployee.getUsername() == null || newEmployee.getUsername() == "") {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Cannot insert employee without Username", null));
        }
        // Kiểm tra xem thông tin có password chưa
        if (newEmployee.getPassword() == null || newEmployee.getPassword() == "") {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Cannot insert employee without Password", null));
        }
        // Kiểm tra xem username đã tồn tại chưa
        Employee foundEmployee = employeeRepository.findByUsername(newEmployee.getUsername());
        if (foundEmployee != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Username already exists", null));
        }
        // Kiểm tra personalCode
        if (employeeRepository.findByPersonalCode(newEmployee.getPersonalCode()) != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Personal already exists", null));
        }
        newEmployee.setPassword(passwordEncoder.encode(newEmployee.getPassword()));
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Insert employee successfully", employeeRepository.save(newEmployee)));
    }

    // Chỉnh sửa thông tin Employee
    public ResponseEntity<ResponseObject> updateEmployeeById(Long id, Employee updatedEmployee) {
        Employee foundEmployee = employeeRepository.findById(id).orElse(null);
        // Kiểm tra id của employee có tồn tại không
        if (foundEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any employee with id: " + id, null));
        }
        // Không được thay đổi username của employee
        if (!foundEmployee.getUsername().equals(updatedEmployee.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "You cannot change the username" , null));
        }
        // Không được thay đổi personal code của employee
        if (!foundEmployee.getPersonalCode().equals(updatedEmployee.getPersonalCode())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "You cannot change the personal code" , null));
        }
        updatedEmployee.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Update employee successfully", employeeRepository.save(updatedEmployee)));
    }

    // Cho nhân viên nghỉ việc
    public ResponseEntity<ResponseObject> deactivateEmployeeById(Long id) {
        Employee foundEmployee = employeeRepository.findById(id).orElse(null);
        // Kiểm tra xem id có tồn tại không
        if (foundEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any employee with id: " + id, null));
        }
        foundEmployee.setActive(false);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Deactivate employee successfully", employeeRepository.save(foundEmployee)));
    } 

    public ResponseEntity<ResponseObject> changeUsername(Long id, UsernameDTO usernameDTO) {
        Employee foundEmployee = employeeRepository.findById(id).orElse(null);
        // Kiểm tra xem id có tồn tại không
        if (foundEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any employee with id: " + id, null));
        }
        
        // nếu username bị sai
        if(!usernameDTO.getOldUsername().equals(foundEmployee.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "old Username is wrong!", null));
        }

        // nếu pwd bị sai
        if(!passwordEncoder.matches(usernameDTO.getPwd(), foundEmployee.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Password is wrong!", null));
        }

        foundEmployee.setUsername(usernameDTO.getNewUsername());
        employeeRepository.save(foundEmployee);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Update username of employee successfully", null));
    } 

    public ResponseEntity<ResponseObject> changePwd(Long id, PwdDTO pwdDTO) {
        Employee foundEmployee = employeeRepository.findById(id).orElse(null);
        // Kiểm tra xem id có tồn tại không
        if (foundEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseObject("Failed", "Cannot find any employee with id: " + id, null));
        }

        // nếu old pwd bị sai
        if(!passwordEncoder.matches(pwdDTO.getOldPassword(), foundEmployee.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(new ResponseObject("Failed", "Old Password is wrong!", null));
        }

        String encryptNewPwd = passwordEncoder.encode(pwdDTO.getNewPassword());
        foundEmployee.setPassword(encryptNewPwd);
        employeeRepository.save(foundEmployee);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject("OK", "Update password of employee successfully", null));
    } 
}
