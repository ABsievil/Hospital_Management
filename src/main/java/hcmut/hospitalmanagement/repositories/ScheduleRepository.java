package hcmut.hospitalmanagement.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDoctorId(Long doctorId);

    List<Schedule> findByPatientId(Long patientId);

    List<Schedule> findByStartTimeAfter(LocalDateTime startTime);

    List<Schedule> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

}
