package hcmut.hospitalmanagement.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.models.Schedule;
import hcmut.hospitalmanagement.repositories.ScheduleRepository;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getActiveSchedule(LocalDateTime startTime) {
        return scheduleRepository.findByStartTimeAfter(startTime);
    }

    public List<Schedule> getSchedulesBetweenTimes(LocalDateTime time1, LocalDateTime time2) {
        return scheduleRepository.findByStartTimeBetween(time1, time2);
    }

    public List<Schedule> getSchedulesByDoctorId(Long doctorId) {
        return scheduleRepository.findByDoctorId(doctorId);
    }

    public List<Schedule> getSchedulesByPatientId(Long patientId) {
        return scheduleRepository.findByPatientId(patientId);
    }

    public Schedule updateSchedule(Long id, Schedule schedule) throws NotFoundException {
        if (scheduleRepository.existsById(id)) {
            schedule.setId(id);
            return scheduleRepository.save(schedule);
        } else {
            throw new NotFoundException();
        }
    }
    
}
