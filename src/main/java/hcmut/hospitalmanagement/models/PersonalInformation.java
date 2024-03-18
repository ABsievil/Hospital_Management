package hcmut.hospitalmanagement.models;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;

@Embeddable
public class PersonalInformation {
    private String name;
    private String nationalID;
    private boolean gender;
    private LocalDate dateOfBirth;
    private String homeTown;
    private String address;
    private String phoneNumber;
    private String occupation;
}
