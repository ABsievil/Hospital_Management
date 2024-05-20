package hcmut.hospitalmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcmut.hospitalmanagement.models.Employee;
import hcmut.hospitalmanagement.models.EmployeeSchedule;
import hcmut.hospitalmanagement.models.PwdDTO;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.models.Role;
import hcmut.hospitalmanagement.models.UsernameDTO;
import hcmut.hospitalmanagement.services.EmployeeScheduleService;
import hcmut.hospitalmanagement.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/Employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeScheduleService employeeScheduleService;

    // Tìm tất cả employee
    @GetMapping("/getAllEmployee")
    public ResponseEntity<ResponseObject> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    // Tìm các employee vẫn đang công tác ở bệnh viện
    @GetMapping("/getActiveEmployee")
    public ResponseEntity<ResponseObject> getActiveEmployee() {
        return employeeService.getActiveEmployee();
    }

    // Tìm các employee đã nghỉ việc
    @GetMapping("/getNonActiveEmployee")
    public ResponseEntity<ResponseObject> getNonActiveEmployee() {
        return employeeService.getNonActiveEmployee();
    }

    // Tìm employee theo id
    @GetMapping("/getEmployeeById/{employeeId}")
    public ResponseEntity<ResponseObject> getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    // Tìm employee theo username
    @GetMapping("/getEmployeeByUsername/{username}")
    public ResponseEntity<ResponseObject> getEmployeeByUsername(@PathVariable String username) {
        return employeeService.getEmployeeByUsername(username);
    }

    // Tìm nhân viên y tế theo tên của họ
    @GetMapping("/getEmployeeByName/{name}")
    public ResponseEntity<ResponseObject> getEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }

    // Tìm bác sĩ
    @GetMapping("/getDoctor")
    public ResponseEntity<ResponseObject> getDoctor() {
        return employeeService.getEmployeeByRole(List.of(Role.ADMIN, Role.DOCTOR));
    }

    // Tìm y tá
    @GetMapping("/getNurse")
    public ResponseEntity<ResponseObject> getNurse() {
        return employeeService.getEmployeeByRole(List.of(Role.NURSE));
    }

    // Tìm nhân viên y tế còn lại (Không phải là Doctor, Nurse)
    @GetMapping("/getStaff")
    public ResponseEntity<ResponseObject> getStaff() {
        return employeeService.getEmployeeByRole(List.of(Role.OTHER));
    }

    // Thêm employee
    @PostMapping("/insertEmployee")
    public ResponseEntity<ResponseObject> insertEmployee(@RequestBody Employee newEmployee) {
        return employeeService.insertEmployee(newEmployee);
    }

    // Chỉnh sửa thông tin Employee
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<ResponseObject> updateEmployeeById(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployeeById(id, updatedEmployee);
    }

    // Cho nhân viên nghỉ việc
    @PutMapping("/deactivateEmployee/{id}")
    public ResponseEntity<ResponseObject> deactivateEmployeeById(@PathVariable Long id) {
        return employeeService.deactivateEmployeeById(id);
    }

    // Thêm lịch cho nhân viên
    @PostMapping("/insertSchedule/{employeeId}")
    public ResponseEntity<ResponseObject> addScheduleForEmployee(@PathVariable Long employeeId, @RequestBody EmployeeSchedule employeeSchedule) {
        return employeeScheduleService.insertScheduleByEmployeeId(employeeId, employeeSchedule);
    }

    // Tìm lịch của nhân viên theo id
    @GetMapping("/getSchedule/{employeeId}")
    public ResponseEntity<ResponseObject> getSchedule(@PathVariable Long employeeId) {
        return employeeScheduleService.getScheduleByEmployeeId(employeeId);
    }

    // Tìm lịch chưa hết hạn cho nhân viên theo id
    @GetMapping("/getActiveSchedule/{employeeId}")
    public ResponseEntity<ResponseObject> getActiveSchedule(@PathVariable Long employeeId) {
        return employeeScheduleService.getActiveScheduleByEmployeeId(employeeId);
    }

    // Thay đổi lịch cho nhân viên 
    @PutMapping("/{employeeId}/updateSchedule/{employeeScheduleId}")
    public ResponseEntity<ResponseObject> updateScheduleByScheduleId(@PathVariable Long employeeId,
    @PathVariable Long employeeScheduleId, @RequestBody EmployeeSchedule updatedEmployeeSchedule) {
        return employeeScheduleService.updateEmployeeScheduleById(employeeScheduleId, employeeId, updatedEmployeeSchedule);
    }

    // Xóa lịch theo id của lịch
    @DeleteMapping("/deleteSchedule/{employeeScheduleId}")
    public ResponseEntity<ResponseObject> deleteScheduleByScheduleId(@PathVariable Long employeeScheduleId) {
        return employeeScheduleService.deleteEmployeeScheduleById(employeeScheduleId);
    }
    
    @PostMapping("/changeUsername/{employeeID}")
    public ResponseEntity<ResponseObject> changeUsername(@PathVariable Long employeeID, @RequestBody UsernameDTO usernameDTO ) {
        return employeeService.changeUsername(employeeID, usernameDTO);
    }

    @PostMapping("/changePwd/{employeeID}")
    public ResponseEntity<ResponseObject> changePwd(@PathVariable Long employeeID, @RequestBody PwdDTO pwdDTO ) {
        return employeeService.changePwd(employeeID, pwdDTO);
    }
}
