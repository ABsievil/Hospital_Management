package hcmut.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hcmut.hospitalmanagement.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
