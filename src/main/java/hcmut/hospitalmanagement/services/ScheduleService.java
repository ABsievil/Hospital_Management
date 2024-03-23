package hcmut.hospitalmanagement.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import hcmut.hospitalmanagement.models.Schedule;
import hcmut.hospitalmanagement.repositories.ScheduleRepository;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // Constructor
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    // Implementations of Methods in SService
    
    // This @Scheduled will automatically execute the below method every minutes
    // so we will delete the schedule if it has exprired
    @Scheduled(cron = "0 * * * * ?")
    public void removeExpiredSchedules() {
        LocalDateTime now = LocalDateTime.now();
        List<Schedule> schedules = scheduleRepository.findAll();
        for (Schedule schedule : schedules) {
            if (schedule.getEndTime().isBefore(now)) {
                scheduleRepository.delete(schedule);
            }
        }
    }
}
