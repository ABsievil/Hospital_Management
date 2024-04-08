package hcmut.hospitalmanagement.database;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import hcmut.hospitalmanagement.models.Employee;
import hcmut.hospitalmanagement.models.EmployeeSchedule;
import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.models.PersonalInformation;
import hcmut.hospitalmanagement.models.Role;
import hcmut.hospitalmanagement.repositories.EmployeeRepository;
import hcmut.hospitalmanagement.repositories.EmployeeScheduleRepository;
import hcmut.hospitalmanagement.repositories.PatientRepository;

@Configuration
public class Database {
    // Logger is used to print information to terminal
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    // CommandLineRunner is used to initialize data for Database (For testing)
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, PatientRepository patientRepository,
            EmployeeScheduleRepository scheduleRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // Doctor testing
                // Employee emp1 = new Employee("user1", passwordEncoder.encode("1231"),
                // Role.DOCTOR);
                // Employee emp2 = new Employee("user2", passwordEncoder.encode("1232"),
                // Role.NURSE);
                // Employee emp3 = new Employee("user3", passwordEncoder.encode("1233"),
                // Role.STAFF);
                // Employee emp4 = new Employee("admin", passwordEncoder.encode("1245"),
                // Role.ADMIN);
                // logger.info("insert employee: " + employeeRepository.save(emp1));
                // logger.info("insert employee: " + employeeRepository.save(emp2));
                // logger.info("insert employee: " + employeeRepository.save(emp3));
                // logger.info("insert employee: " + employeeRepository.save(emp4));

                // Employee
                PersonalInformation Pemp1 = new PersonalInformation("ABsievil", "VN", true, null,
                        "Phong nha Ke Bang, QB", null, "0911336607", "Q9, TP.HCM", "developer", "Hung Nguyen",
                        "0984734456", "Phong Nha Ke Bang, QB", "9999999999", null, null, null, null, null, null, null);

                Employee emp1 = new Employee(null, true, null, null, null, null, null, Pemp1, "admin",
                        passwordEncoder.encode("1245"), Role.DOCTOR);
                logger.info("insert employee: " + employeeRepository.save(emp1));

                PersonalInformation Pemp2 = new PersonalInformation("Bach Khoa", "123456", true, "Kinh", "Không",
                        LocalDate.of(2004, 07, 04), "Ho Chi Minh city", "0123456789",
                        "236 Ly Thuong Kiet, P12, Q10, HCM", "aaa@hcmut.edu.vn", "Doctor", "Khoa Hoc Tu Nhien",
                        "Friend", "Doctor", "0123456789", "0123456789", "Di An, Binh Duong", "123456",
                        LocalDate.of(2020, 01, 01), LocalDate.of(2025, 01, 01));
                Employee emp2 = new Employee(null, true, "Doctor tim mạch", "Trưởng khoa", "Học vị", "Học hàm",
                        "Đại học Quốc Gia", Pemp2, "bachkhoa", passwordEncoder.encode("1234"), Role.ADMIN);
                logger.info("insert employee: " + employeeRepository.save(emp2));

                // Patient
                PersonalInformation patientInfo1 = new PersonalInformation("Nguyễn Văn A", "048123456789", true, null,
                        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
                Patient patient1 = new Patient(null, patientInfo1, null, null);

                logger.info("insert patient: " + patientRepository.save(patient1));

                // Schedule
                EmployeeSchedule schedule1 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 0, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 8, 0, 0), emp1, 1L,
                        patient1.getInformation().getName(), "201");
                EmployeeSchedule schedule2 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 8, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 9, 0, 0), emp2, 1L,
                        patient1.getInformation().getName(), "202");
                EmployeeSchedule schedule3 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 12, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 14, 0, 0), emp1, 1L,
                        patient1.getInformation().getName(), "203");
                EmployeeSchedule schedule4 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 15, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 16, 0, 0), emp2, 1L,
                        patient1.getInformation().getName(), "204");
                EmployeeSchedule schedule5 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 17, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 18, 0, 0), emp1, 1L,
                        patient1.getInformation().getName(), "205");
                EmployeeSchedule schedule6 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 18, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 19, 0, 0), emp2, 1L,
                        patient1.getInformation().getName(), "205");
                EmployeeSchedule schedule7 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 19, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 20, 0, 0), emp1, 1L,
                        patient1.getInformation().getName(), "205");
                EmployeeSchedule schedule8 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 20, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 21, 0, 0), emp1, 1L,
                        patient1.getInformation().getName(), "205");
                EmployeeSchedule schedule9 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 21, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 22, 0, 0), emp1, 1L,
                        patient1.getInformation().getName(), "205");
                EmployeeSchedule schedule10 = new EmployeeSchedule(null, "Title", "Description",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 22, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 23, 0, 0), emp1, 1L,
                        patient1.getInformation().getName(), "205");
                logger.info("insert schedule: " + scheduleRepository.save(schedule1));
                logger.info("insert schedule: " + scheduleRepository.save(schedule2));
                logger.info("insert schedule: " + scheduleRepository.save(schedule3));
                logger.info("insert schedule: " + scheduleRepository.save(schedule4));
                logger.info("insert schedule: " + scheduleRepository.save(schedule5));
                logger.info("insert schedule: " + scheduleRepository.save(schedule6));
                logger.info("insert schedule: " + scheduleRepository.save(schedule7));
                logger.info("insert schedule: " + scheduleRepository.save(schedule8));
                logger.info("insert schedule: " + scheduleRepository.save(schedule9));
                logger.info("insert schedule: " + scheduleRepository.save(schedule10));
            }
        };
    }
}
