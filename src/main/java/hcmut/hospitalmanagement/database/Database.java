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
import hcmut.hospitalmanagement.models.Medicine;
import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.models.PersonalInformation;
import hcmut.hospitalmanagement.models.Role;
import hcmut.hospitalmanagement.repositories.EmployeeRepository;
import hcmut.hospitalmanagement.repositories.EmployeeScheduleRepository;
import hcmut.hospitalmanagement.repositories.MedicineRepository;
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
            EmployeeScheduleRepository scheduleRepository,
            MedicineRepository medicineRepository) {
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

                PersonalInformation Pemp1 = new PersonalInformation("ABsievil", "", "123456", true, null, null, null,
                        "Phong nha Ke Bang, QB", "0911336607", "Q9, TP.HCM", "admin@hcmut.edu.vn", "Manager",
                        "31005230", "", "", null, null, null, null, null, null);

                Employee emp1 = new Employee(null, true, null, null, null, null, null, null, Pemp1, "admin",
                        passwordEncoder.encode("1245"), Role.ADMIN);
                logger.info("insert employee: " + employeeRepository.save(emp1));

                PersonalInformation Pemp2 = new PersonalInformation("Mạnh Hùng", "Nguyễn", "123456", false, "Kinh",
                        "Không",
                        LocalDate.of(2004, 07, 04), "Nghệ An", "0123456789",
                        "KTX Khu A", "Hungdb@hcmut.edu.vn", "Doctor", "2211337", "Nguyễn Cao Cường",
                        "Friend", "Doctor", "0911336607", "0339242722", "Q9, TP.HCM", LocalDate.of(2020, 01, 01), LocalDate.of(2025, 01, 01));
                Employee emp2 = new Employee(null, true, "Tim mạch", "Trưởng khoa Hóa", "Ko có", "F(n) = x^2",
                        "Đại học BK HCM", "2210000", Pemp2, "nmhung", passwordEncoder.encode("hungdb"), Role.DOCTOR);
                logger.info("insert employee: " + employeeRepository.save(emp2));

                // Patient
                PersonalInformation patientInfo1 = new PersonalInformation("A", "Nguyễn Văn", null, true, null,
                        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null
                );
                Patient patient1 = new Patient(null, patientInfo1, null, null);

                logger.info("insert patient: " + patientRepository.save(patient1));

                // Schedule
                EmployeeSchedule schedule1 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 0, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 8, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "201");
                EmployeeSchedule schedule2 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 8, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 9, 0, 0), emp2, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "202");
                EmployeeSchedule schedule3 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 12, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 14, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "203");
                EmployeeSchedule schedule4 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 15, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 16, 0, 0), emp2, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "204");
                EmployeeSchedule schedule5 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 17, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 18, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule6 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 18, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 19, 0, 0), emp2, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule7 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 19, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 20, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule8 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 20, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 21, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule9 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 21, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 22, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule10 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 22, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 23, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");

                EmployeeSchedule schedule11 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().plusDays(1).getDayOfMonth(), 7, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().plusDays(1).getDayOfMonth(), 9, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule12 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().plusDays(2).getDayOfMonth(), 7, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().plusDays(2).getDayOfMonth(), 9, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule13 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().plusDays(3).getDayOfMonth(), 7, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().plusDays(3).getDayOfMonth(), 9, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule14 = new EmployeeSchedule(null, "Khám bệnh", "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().plusDays(4).getDayOfMonth(), 7, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().plusDays(4).getDayOfMonth(), 9, 0, 0), emp1, 1L,
                        patient1.getInformation().getFirstName(), patient1.getInformation().getLastName(), "205");
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
                logger.info("insert schedule: " + scheduleRepository.save(schedule11));
                logger.info("insert schedule: " + scheduleRepository.save(schedule12));
                logger.info("insert schedule: " + scheduleRepository.save(schedule13));
                logger.info("insert schedule: " + scheduleRepository.save(schedule14));

                // medicine
                Medicine medicine1 = new Medicine(null, "Paracetamol", "Thành phần của Paracetamol",
                        "Công dụng của Paracetamol","Lưu ý về paracetamol", true);
                Medicine medicine2 = new Medicine(null, "Eugica", "Thành phần của Eugica",
                        "Công dụng của Eugica","Lưu ý về Eugica", true);
                Medicine medicine3 = new Medicine(null, "Sorbitol", "Thành phần của Sorbitol",
                        "Công dụng của Sorbitol","Lưu ý về Sorbitol", false);
                Medicine medicine4 = new Medicine(null, "Panadol Extra", "Thành phần của Panadol Extra",
                        "Công dụng của Panadol Extra","Lưu ý về Panadol Extra", true);        
                Medicine medicine5 = new Medicine(null, "Vitamin C", "Thành phần của Vitamin C",
                        "Công dụng của Vitamin C","Lưu ý về Vitamin C", true);
                Medicine medicine6 = new Medicine(null, "Penicillin", "Thành phần của Penicillin",
                        "Công dụng của Penicillin","Lưu ý về Penicillin", true);        
                logger.info("insert medicine: " + medicineRepository.save(medicine1));
                logger.info("insert medicine: " + medicineRepository.save(medicine2));
                logger.info("insert medicine: " + medicineRepository.save(medicine3));
                logger.info("insert medicine: " + medicineRepository.save(medicine4));
                logger.info("insert medicine: " + medicineRepository.save(medicine5));
                logger.info("insert medicine: " + medicineRepository.save(medicine6));
            }
        };
    }
}
