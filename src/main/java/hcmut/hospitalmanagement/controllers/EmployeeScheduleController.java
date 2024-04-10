package hcmut.hospitalmanagement.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hcmut.hospitalmanagement.models.EmployeeSchedule;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.services.EmployeeScheduleService;

@RestController
@RequestMapping("/api/v1/EmployeeSchedule")
public class EmployeeScheduleController {
    @Autowired
    private EmployeeScheduleService employeeScheduleService;

    // Tìm tất cả lịch của nhân viên
    @GetMapping("/getAllSchedule")
    public ResponseEntity<ResponseObject> getAllEmployeeSchedule() {
        return employeeScheduleService.getAllEmployeeSchedule();
    }

    // Tìm lịch chưa hết hạn
    @GetMapping("/getActiveSchedule")
    public ResponseEntity<ResponseObject> getActiveEmployeeSchedule() {
        return employeeScheduleService.getActiveEmployeeSchedule();
    }

    // Tìm lịch có thời gian bắt đầu nằm giữa startTime và endTime
    @GetMapping("/getScheduleBetweenTime")
    public ResponseEntity<ResponseObject> getScheduleBetweenTime(
        @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
        @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        return employeeScheduleService.getScheduleBetweenTime(startTime, endTime);
    }

    // Tìm tất cả lịch của nhân viên
    @GetMapping("/getScheduleByEmployeeId/{employeeId}")
    public ResponseEntity<ResponseObject> getScheduleByEmployeeId(@PathVariable Long employeeId) {
        return employeeScheduleService.getScheduleByEmployeeId(employeeId);
    }

    // Tìm tất cả lịch chưa hết hạn của nhân viên
    @GetMapping("/getActiveScheduleByEmployeeId/{employeeId}")
    public ResponseEntity<ResponseObject> getActiveScheduleByEmployeeId(@PathVariable Long employeeId) {
        return employeeScheduleService.getActiveScheduleByEmployeeId(employeeId);
    }

    //
    @GetMapping("/getScheduleByEmployeeIdBetweenTime/{employeeId}")
    public ResponseEntity<ResponseObject> getScheduleByEmployeeIdBetweenTime (
        @PathVariable Long employeeId,
        @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
        @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        return employeeScheduleService.getScheduleByEmployeeIdBetweenTime(employeeId, startTime, endTime);
    }

    // Thêm lịch cho nhân viên 
    @PostMapping("/insertSchedule/{employeeId}")
    public ResponseEntity<ResponseObject> insertScheduleByEmployeeId(@PathVariable Long employeeId, 
    @RequestBody EmployeeSchedule newEmployeeSchedule) {
        return employeeScheduleService.insertScheduleByEmployeeId(employeeId, newEmployeeSchedule);
    }

    // Chỉnh sửa lịch cho nhân viên
    @PutMapping("/{employeeScheduleId}/updateSchedule/{employeeId}")
    public ResponseEntity<ResponseObject> updateEmployeeScheduleById(@PathVariable Long employeeScheduleId, 
    @PathVariable Long employeeId, @RequestBody EmployeeSchedule updatedEmployeeSchedule) {
        return employeeScheduleService.updateEmployeeScheduleById(employeeScheduleId, employeeId, updatedEmployeeSchedule);
    }

    // Xóa lịch theo id của lịch
    @DeleteMapping("/deleteSchedule/{employeeScheduleId}")
    public ResponseEntity<ResponseObject> deleteEmployeeScheduleById(@PathVariable Long employeeScheduleId) {
        return employeeScheduleService.deleteEmployeeScheduleById(employeeScheduleId);
    }
}  
