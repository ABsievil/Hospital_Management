package hcmut.hospitalmanagement.controllers;

import org.springframework.web.bind.annotation.RestController;

import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.models.Schedule;
import hcmut.hospitalmanagement.services.ScheduleService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/Schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;


    // Get All Schedules
    @GetMapping("/getAllSchedules")
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // Get Schedule By Id
    @GetMapping("/getScheduleById/{id}")
    public ResponseEntity<ResponseObject> getScheduleById(@PathVariable Long id) {
        Optional<Schedule> foundSchedule = scheduleService.getScheduleById(id);
        if (!foundSchedule.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "Cannot find Schedule with id: " + id, null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Querry Schedule Successfully",
                        scheduleService.saveSchedule(foundSchedule.get())));
    }

    // Get Schedule Of Doctor with doctorId
    @GetMapping("/getScheduleByDoctorId/{doctorId}")
    public List<Schedule> getSchedulesByDoctorId(@PathVariable Long doctorId) {
        return scheduleService.getSchedulesByDoctorId(doctorId);
    }

    // Get Schedule Of Patient with patientId
    @GetMapping("/getScheduleByPatientId/{patientId}")
    public List<Schedule> getSchedulesByPatientId(@PathVariable Long patientId) {
        return scheduleService.getSchedulesByPatientId(patientId);
    }

    // Get The Active Schedule (Schedule that still not expired)
    @GetMapping("/getActiveSchedule")
    public List<Schedule> getActiveSchedule(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime) {
        return scheduleService.getActiveSchedule(startTime);
    }

    // Get Schedule Between Time
    @GetMapping("/getScheduleBetweenTime")
    public List<Schedule> getSchedulesBetweenTime(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return scheduleService.getSchedulesBetweenTimes(startTime, endTime);
    }


    // Insert new Schedule
    @PostMapping("/insertSchedule")
    public ResponseEntity<Object> createSchedule(@RequestBody Schedule schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject("OK", "Insert Schedule Successfully", createdSchedule));
    }


    @PutMapping("/updateSchedule/{id}")
    public ResponseEntity<ResponseObject> updateSchedule(@PathVariable Long id, @RequestBody Schedule scheduleDetails)
            throws NotFoundException {
        Optional<Schedule> foundSchedule = scheduleService.getScheduleById(id);
        if (!foundSchedule.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Failed", "Cannot find Schedule with id: " + id, null));
        }
        Schedule updatedSchedule = scheduleService.updateSchedule(id, scheduleDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("OK", "Update Successfully for Schedule with id: " + id, updatedSchedule));
    }
}
