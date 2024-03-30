// package hcmut.hospitalmanagement.database;

// import java.time.LocalDateTime;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import hcmut.hospitalmanagement.models.Doctor;
// import hcmut.hospitalmanagement.models.Patient;
// import hcmut.hospitalmanagement.models.PersonalInformation;
// import hcmut.hospitalmanagement.models.Schedule;
// import hcmut.hospitalmanagement.repositories.DoctorRepository;
// import hcmut.hospitalmanagement.repositories.PatientRepository;
// import hcmut.hospitalmanagement.repositories.ScheduleRepository;

// @Configuration
// public class Database {
//     // Logger is used to print information to terminal
//     private static final Logger logger = LoggerFactory.getLogger(Database.class);
    
//     @Autowired
//     PasswordEncoder passwordEncoder;

//     // CommandLineRunner is used to initialize data for Database (For testing)
//     @Bean
//     CommandLineRunner initDatabase(DoctorRepository doctorRepository, PatientRepository patientRepository,
//             ScheduleRepository scheduleRepository) {
//         return new CommandLineRunner() {
//             @Override
//             public void run(String... args) throws Exception {
//                 // Doctor testing
//                 Doctor doctor1 = new Doctor(true, null, null, null);
//                 Doctor doctor2 = new Doctor(true, null, null, null);
//                 Doctor doctor3 = new Doctor(false, null, null, null);
//                 Doctor doctor4 = new Doctor(false, null, null, null);
//                 logger.info("insert doctor: " + doctorRepository.save(doctor1));
//                 logger.info("insert doctor: " + doctorRepository.save(doctor2));
//                 logger.info("insert doctor: " + doctorRepository.save(doctor3));
//                 logger.info("insert doctor: " + doctorRepository.save(doctor4));

//                 Patient patient1 = new Patient("user", passwordEncoder.encode("1234"),true, null, null, null, null);
//                 Patient patient2 = new Patient("admin", passwordEncoder.encode("1245"),false, null, null, null, null);
//                 Patient patient3 = new Patient("john", passwordEncoder.encode("nick"),true, null, null, null, null);
//                 Patient patient4 = new Patient("username", passwordEncoder.encode("password"),false, null, null, null, null);
//                 logger.info("insert patient: " + patientRepository.save(patient1));
//                 logger.info("insert patient: " + patientRepository.save(patient2));
//                 logger.info("insert patient: " + patientRepository.save(patient3));
//                 logger.info("insert patient: " + patientRepository.save(patient4));
//                 // Schedule testing
//                 Schedule schedule1 = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusMinutes(5), "Title",
//                         "Description", doctor1, patient1);
//                 Schedule schedule2 = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusMinutes(5), "Title",
//                         "Description", doctor1, patient2);
//                 Schedule schedule3 = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusMinutes(5), "Title",
//                         "Description", doctor2, patient1);
//                 Schedule schedule4 = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusMinutes(5), "Title",
//                         "Description", doctor2, patient2);
//                 logger.info("insert schedule: " + scheduleRepository.save(schedule1));
//                 logger.info("insert schedule: " + scheduleRepository.save(schedule2));
//                 logger.info("insert schedule: " + scheduleRepository.save(schedule3));
//                 logger.info("insert schedule: " + scheduleRepository.save(schedule4));
//             }
//         };
//     }
// }
