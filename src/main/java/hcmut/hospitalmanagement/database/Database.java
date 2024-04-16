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
import hcmut.hospitalmanagement.models.Equipment;
import hcmut.hospitalmanagement.models.EquipmentType;
import hcmut.hospitalmanagement.models.Medicine;
import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.models.PersonalInformation;
import hcmut.hospitalmanagement.models.Rate;
import hcmut.hospitalmanagement.models.Role;
import hcmut.hospitalmanagement.models.TreatmentHistory;
import hcmut.hospitalmanagement.repositories.EmployeeRepository;
import hcmut.hospitalmanagement.repositories.EmployeeScheduleRepository;
import hcmut.hospitalmanagement.repositories.EquipmentRepository;
import hcmut.hospitalmanagement.repositories.MedicineRepository;
import hcmut.hospitalmanagement.repositories.PatientRepository;
import hcmut.hospitalmanagement.repositories.TreatmentHistoryRepository;

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
            MedicineRepository medicineRepository,
            TreatmentHistoryRepository treatmentHistoryRepository,
            EquipmentRepository equipmentRepository) {
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

                PersonalInformation Pemp1 = new PersonalInformation("ABsievil", "", "123456", true,
                        null, null, null,
                        "Phong nha Ke Bang, QB", "0911336607", "Q9, TP.HCM",
                        "admin@hcmut.edu.vn", "Doctor",
                        "31005230", "", "", null, null, null, null, null, null);

                Employee emp1 = new Employee(null, true, null, "Giám đốc bệnh viện", "Tiến sĩ", "Giáo sư",
                        "ĐH Bách Khoa", null, Pemp1,
                        "admin",
                        passwordEncoder.encode("1245"), Role.ADMIN);

                PersonalInformation Pemp2 = new PersonalInformation("Mạnh Hùng", "Nguyễn", "123456",
                        false, "Kinh",
                        "Không",
                        LocalDate.of(2004, 07, 04), "Nghệ An", "0123456789",
                        "KTX Khu A", "Hungdb@hcmut.edu.vn", "Doctor", "2211337",
                        "Nguyễn Cao Cường",
                        "Friend", "Doctor", "0911336607", "0339242722", "Q9, TP.HCM",
                        LocalDate.of(2020, 01, 01), LocalDate.of(2025, 01, 01));
                Employee emp2 = new Employee(null, true, "Tim mạch", "Trưởng khoa Hóa", "Tiến sĩ",
                        "Giáo sư",
                        "Đại học BK HCM", "2210000", Pemp2, "nmhung",
                        passwordEncoder.encode("hungdb"), Role.DOCTOR);
                PersonalInformation Pemp3 = new PersonalInformation("Strange", "Steven", "123456",
                        false, "Kinh",
                        "Không",
                        LocalDate.of(2004, 07, 04), "Nghệ An", "0123456789",
                        "KTX Khu A", "Hungdb@hcmut.edu.vn", "Doctor", "2211337",
                        "Nguyễn Cao Cường",
                        "Friend", "Doctor", "0911336607", "0339242722", "Q9, TP.HCM",
                        LocalDate.of(2020, 01, 01), LocalDate.of(2025, 01, 01));
                Employee emp3 = new Employee(null, true, "Tim mạch", "Trưởng khoa Tim mạch", "Tiến sĩ",
                        "Giáo sư",
                        "Đại học BK HCM", "2211234", Pemp3, "doctor",
                        passwordEncoder.encode("1234"), Role.DOCTOR);
                logger.info("insert employee: " + employeeRepository.save(emp1));
                logger.info("insert employee: " + employeeRepository.save(emp2));
                logger.info("insert employee: " + employeeRepository.save(emp3));

                // Patient
                PersonalInformation patientInfo1 = new PersonalInformation("Văn A", "Nguyễn", null,
                        true, null,
                        null, null, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null);
                Patient patient1 = new Patient(null, patientInfo1, null);
                PersonalInformation patientInfo2 = new PersonalInformation("Kimmich", "Joshua", null,
                        false, null, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null);
                Patient patient2 = new Patient(null, patientInfo2, null);
                PersonalInformation patientInfo3 = new PersonalInformation("Reus", "Marco", null,
                        false, null, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null);
                Patient patient3 = new Patient(null, patientInfo3, null);
                PersonalInformation patientInfo4 = new PersonalInformation("Ozil", "Mesut", null,
                        false, null, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null);
                Patient patient4 = new Patient(null, patientInfo4, null);
                logger.info("insert patient: " + patientRepository.save(patient1));
                logger.info("insert patient: " + patientRepository.save(patient2));
                logger.info("insert patient: " + patientRepository.save(patient3));
                logger.info("insert patient: " + patientRepository.save(patient4));

                // Schedule
                EmployeeSchedule schedule1 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 0, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 8, 0, 0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "201");
                EmployeeSchedule schedule2 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 8, 0, 0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 9, 0, 0),
                        emp2, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "202");
                EmployeeSchedule schedule3 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 12, 0,
                                0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 14, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "203");
                EmployeeSchedule schedule4 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 15, 0,
                                0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 16, 0,
                                0),
                        emp2, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "204");
                EmployeeSchedule schedule5 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 17, 0,
                                0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 18, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule6 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 18, 0,
                                0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 19, 0,
                                0),
                        emp2, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule7 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 19, 0,
                                0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 20, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule8 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 20, 0,
                                0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 21, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule9 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 21, 0,
                                0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 22, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule10 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 22, 0,
                                0),
                        LocalDateTime.of(2024, 4, LocalDateTime.now().getDayOfMonth(), 23, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");

                EmployeeSchedule schedule11 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().plusDays(1).getDayOfMonth(), 7, 0,
                                0),
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().plusDays(1).getDayOfMonth(), 9, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule12 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().plusDays(2).getDayOfMonth(), 7, 0,
                                0),
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().plusDays(2).getDayOfMonth(), 9, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule13 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().plusDays(3).getDayOfMonth(), 7, 0,
                                0),
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().plusDays(3).getDayOfMonth(), 9, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule14 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().plusDays(4).getDayOfMonth(), 7, 0,
                                0),
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().plusDays(4).getDayOfMonth(), 9, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule15 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().minusDays(1).getDayOfMonth(), 7, 0,
                                0),
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().minusDays(1).getDayOfMonth(), 9, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                EmployeeSchedule schedule16 = new EmployeeSchedule(null, "Khám bệnh",
                        "Anh Nguyễn Văn A đến tái khám",
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().minusDays(2).getDayOfMonth(), 7, 0,
                                0),
                        LocalDateTime.of(2024, 4,
                                LocalDateTime.now().minusDays(2).getDayOfMonth(), 9, 0,
                                0),
                        emp1, 1L,
                        patient1.getInformation().getFirstName(),
                        patient1.getInformation().getLastName(), "205");
                logger.info("insert schedule: " + scheduleRepository.save(schedule5));
                logger.info("insert schedule: " + scheduleRepository.save(schedule4));
                logger.info("insert schedule: " + scheduleRepository.save(schedule3));
                logger.info("insert schedule: " + scheduleRepository.save(schedule2));
                logger.info("insert schedule: " + scheduleRepository.save(schedule1));
                logger.info("insert schedule: " + scheduleRepository.save(schedule6));
                logger.info("insert schedule: " + scheduleRepository.save(schedule7));
                logger.info("insert schedule: " + scheduleRepository.save(schedule8));
                logger.info("insert schedule: " + scheduleRepository.save(schedule9));
                logger.info("insert schedule: " + scheduleRepository.save(schedule10));
                logger.info("insert schedule: " + scheduleRepository.save(schedule11));
                logger.info("insert schedule: " + scheduleRepository.save(schedule12));
                logger.info("insert schedule: " + scheduleRepository.save(schedule13));
                logger.info("insert schedule: " + scheduleRepository.save(schedule14));
                logger.info("insert schedule: " + scheduleRepository.save(schedule15));
                logger.info("insert schedule: " + scheduleRepository.save(schedule16));

                // medicine
                Medicine medicine1 = new Medicine(null, "Paracetamol", "Thành phần của Paracetamol",
                        "Công dụng của Paracetamol", "Lưu ý về paracetamol", true);
                Medicine medicine2 = new Medicine(null, "Eugica", "Thành phần của Eugica",
                        "Công dụng của Eugica", "Lưu ý về Eugica", true);
                Medicine medicine3 = new Medicine(null, "Sorbitol", "Thành phần của Sorbitol",
                        "Công dụng của Sorbitol", "Lưu ý về Sorbitol", false);
                Medicine medicine4 = new Medicine(null, "Panadol Extra", "Thành phần của Panadol Extra",
                        "Công dụng của Panadol Extra", "Lưu ý về Panadol Extra", true);
                Medicine medicine5 = new Medicine(null, "Vitamin C", "Thành phần của Vitamin C",
                        "Công dụng của Vitamin C", "Lưu ý về Vitamin C", true);
                Medicine medicine6 = new Medicine(null, "Penicillin", "Thành phần của Penicillin",
                        "Công dụng của Penicillin", "Lưu ý về Penicillin", true);
                logger.info("insert medicine: " + medicineRepository.save(medicine1));
                logger.info("insert medicine: " + medicineRepository.save(medicine2));
                logger.info("insert medicine: " + medicineRepository.save(medicine3));
                logger.info("insert medicine: " + medicineRepository.save(medicine4));
                logger.info("insert medicine: " + medicineRepository.save(medicine5));
                logger.info("insert medicine: " + medicineRepository.save(medicine6));

                // TreatmentHistory
                TreatmentHistory th1 = new TreatmentHistory(null, 1L, "Văn A", "Nguyễn", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 4, 14), LocalDate.of(2024, 4, 14), 100.00, null, Rate.VERYGOOD);
                TreatmentHistory th2 = new TreatmentHistory(null, 1L, "Văn A", "Nguyễn", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 4, 14), LocalDate.of(2024, 4, 14), 140.23, null, Rate.GOOD);
                TreatmentHistory th3 = new TreatmentHistory(null, 1L, "Văn A", "Nguyễn", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 4, 14), LocalDate.of(2024, 4, 14), 170.69, null, Rate.GOOD);
                TreatmentHistory th4 = new TreatmentHistory(null, 2L, "Kimmich", "Joshua", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 4, 14), LocalDate.of(2024, 4, 14), 170.69, null, Rate.VERYGOOD);
                TreatmentHistory th5 = new TreatmentHistory(null, 2L, "Kimmich", "Joshua", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 4, 14), LocalDate.of(2024, 4, 14), 200.15, null, Rate.NORMAL);
                TreatmentHistory th6 = new TreatmentHistory(null, 3L, "Reus", "Marco", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 4, 14), LocalDate.of(2024, 4, 14), 200.15, null, Rate.NORMAL);
                TreatmentHistory th7 = new TreatmentHistory(null, 4L, "Ozil", "Mesut", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 4, 14), LocalDate.of(2024, 4, 14), 200.15, null, Rate.VERYGOOD);
                TreatmentHistory th8 = new TreatmentHistory(null, 2L, "Kimmich", "Joshua", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 3, 14), LocalDate.of(2024, 4, 14), 10, null, Rate.VERYGOOD);
                TreatmentHistory th9 = new TreatmentHistory(null, 4L, "Ozil", "Mesut", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 3, 14), LocalDate.of(2024, 4, 14), 100, null, Rate.VERYBAD);
                TreatmentHistory th10 = new TreatmentHistory(null, 1L, "Văn A", "Nguyễn", 2L, "Mạnh Hùng", "Nguyễn",
                        "cancer", LocalDate.of(2024, 3, 14), LocalDate.of(2024, 4, 14), 100.00, null, Rate.VERYGOOD);
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th1));
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th2));
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th3));
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th4));
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th5));
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th6));
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th7));
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th8));
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th9));
                logger.info("insert treatment history: " + treatmentHistoryRepository.save(th10));

                // Equipment
                Equipment equip1 = new Equipment(null, "Kim tiêm", "101", "Lưu ý", true, EquipmentType.SMALL);
                Equipment equip2 = new Equipment(null, "Kéo", "101", "Lưu ý", false, EquipmentType.SMALL);
                Equipment equip3 = new Equipment(null, "Máy chụp X-quang", "101", "Lưu ý", true, EquipmentType.BIG);
                Equipment equip4 = new Equipment(null, "Máy thở y tế", "101", "Lưu ý", false, EquipmentType.BIG);
                logger.info("insert equipment: " + equipmentRepository.save(equip1));
                logger.info("insert equipment: " + equipmentRepository.save(equip2));
                logger.info("insert equipment: " + equipmentRepository.save(equip3));
                logger.info("insert equipment: " + equipmentRepository.save(equip4));
            }
        };
    }
}
