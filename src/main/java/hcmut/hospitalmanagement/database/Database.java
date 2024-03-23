package hcmut.hospitalmanagement.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hcmut.hospitalmanagement.models.Doctor;
import hcmut.hospitalmanagement.models.Patient;
import hcmut.hospitalmanagement.repositories.DoctorRepository;
import hcmut.hospitalmanagement.repositories.PatientRepository;

@Configuration
public class Database {
    // Logger is used to print information to terminal
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    // CommandLineRunner is used to initialize data for Database (For testing)
    @Bean
    CommandLineRunner initDatabase(DoctorRepository doctorRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Doctor doctor1 = new Doctor(true, null, null, null);
                Doctor doctor2 = new Doctor(true, null, null, null);
                Doctor doctor3 = new Doctor(false, null, null, null);
                Doctor doctor4 = new Doctor(false, null, null, null);
                logger.info("insert data: " + doctorRepository.save(doctor1));
                logger.info("insert data: " + doctorRepository.save(doctor2));
                logger.info("insert data: " + doctorRepository.save(doctor3));
                logger.info("insert data: " + doctorRepository.save(doctor4));
            }
        };
    }
}
