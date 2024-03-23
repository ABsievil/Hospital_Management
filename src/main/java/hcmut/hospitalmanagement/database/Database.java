package hcmut.hospitalmanagement.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.repositories.PatientRepository;

@Configuration
public class Database {
    // Logger is used to print information to terminal
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    // CommandLineRunner is used to initialize data for Database (For testing)
    // @Bean
    // CommandLineRunner initDatabase(PatientRepository patientRepository) {
    //     return new CommandLineRunner() {
    //         @Override
    //         public void run(String... args) throws Exception {
    //             Patient patient1 = new Patient("Nick" ,"HIV", "Positive", "Operation Tomorrow");
    //             Patient patient2 = new Patient("John" ,"none", "Negative", "Get more vitamins");
    //             logger.info("insert data: " + patientRepository.save(patient1));
    //             logger.info("insert data: " + patientRepository.save(patient2));
    //         }
    //     };
    // }
}
