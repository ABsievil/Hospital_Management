package hcmut.hospitalmanagement.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.EmployeeSchedule;

public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, Long>{
    List<EmployeeSchedule> findByEmployeeId(Long employeeId);

    List<EmployeeSchedule> findByStartTimeAfter(LocalDateTime startTime);

    List<EmployeeSchedule> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<EmployeeSchedule> findByStartTimeBetweenOrderByStartTime(LocalDateTime startTime, LocalDateTime endTime);

    List<EmployeeSchedule> findByEmployeeIdAndStartTimeAfter(Long employeeId, LocalDateTime startTime);

    List<EmployeeSchedule> findByEmployeeIdAndStartTimeBetween(Long employeeId, LocalDateTime startTime, LocalDateTime endTime);
}
