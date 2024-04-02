// package hcmut.hospitalmanagement.database;

// import java.time.LocalDateTime;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import hcmut.hospitalmanagement.models.Employee;
// import hcmut.hospitalmanagement.models.Patient;
// import hcmut.hospitalmanagement.models.PersonalInformation;
// import hcmut.hospitalmanagement.models.Role;
// import hcmut.hospitalmanagement.repositories.EmployeeRepository;
// import hcmut.hospitalmanagement.repositories.PatientRepository;

// @Configuration
// public class Database {
//     // Logger is used to print information to terminal
//     private static final Logger logger = LoggerFactory.getLogger(Database.class);
    
//     @Autowired
//     PasswordEncoder passwordEncoder;

//     // CommandLineRunner is used to initialize data for Database (For testing)
//     @Bean
//     CommandLineRunner initDatabase(EmployeeRepository employeeRepository, PatientRepository patientRepository) {
//         return new CommandLineRunner() {
//             @Override
//             public void run(String... args) throws Exception {
//                 // Doctor testing
//                 Employee emp1 = new Employee(null, true, null, null, "user1", passwordEncoder.encode("1231"), Role.DOCTOR);
//                 Employee emp2 = new Employee(null, false, null, null, "user2", passwordEncoder.encode("1232"), Role.NURSE);
//                 Employee emp3 = new Employee(null, true, null, null, "user3", passwordEncoder.encode("1233"), Role.OTHER);
//                 Employee emp4 = new Employee(null, false, null, null, "admin", passwordEncoder.encode("1245"), Role.ADMIN);
//                 logger.info("insert employee: " + employeeRepository.save(emp1));
//                 logger.info("insert employee: " + employeeRepository.save(emp2));
//                 logger.info("insert employee: " + employeeRepository.save(emp3));
//                 logger.info("insert employee: " + employeeRepository.save(emp4));

//                 // Patient patient1 = new Patient("user", passwordEncoder.encode("1234"),true, null, null, null, null);
//                 // Patient patient2 = new Patient("admin", passwordEncoder.encode("1245"),false, null, null, null, null);
//                 // Patient patient3 = new Patient("john", passwordEncoder.encode("nick"),true, null, null, null, null);
//                 // Patient patient4 = new Patient("username", passwordEncoder.encode("password"),false, null, null, null, null);
//                 // logger.info("insert patient: " + patientRepository.save(patient1));
//                 // logger.info("insert patient: " + patientRepository.save(patient2));
//                 // logger.info("insert patient: " + patientRepository.save(patient3));
//                 // logger.info("insert patient: " + patientRepository.save(patient4));
//             }
//         };
//     }
// }
