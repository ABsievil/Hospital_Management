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
    private String phoneNumber;
    private String address;
    private String occupation;

    // Bảo hiểm y tế (Health Insurance or HI) 
    private String hiCode;
    private LocalDate hiStartDate;
    private LocalDate hiEndDate;
    
    // Constructor 
    public PersonalInformation() {
    }

    public PersonalInformation(String name, String nationalID, boolean gender, LocalDate dateOfBirth, String homeTown, String phoneNumber, String address, String occupation, String hiCode, LocalDate hiStartDate, LocalDate hiEndDate) {
        this.name = name;
        this.nationalID = nationalID;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.homeTown = homeTown;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.occupation = occupation;
        this.hiCode = hiCode;
        this.hiStartDate = hiStartDate;
        this.hiEndDate = hiEndDate;
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalID() {
        return this.nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public boolean isGender() {
        return this.gender;
    }

    public boolean getGender() {
        return this.gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomeTown() {
        return this.homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHiCode() {
        return this.hiCode;
    }

    public void setHiCode(String hiCode) {
        this.hiCode = hiCode;
    }

    public LocalDate getHiStartDate() {
        return this.hiStartDate;
    }

    public void setHiStartDate(LocalDate hiStartDate) {
        this.hiStartDate = hiStartDate;
    }

    public LocalDate getHiEndDate() {
        return this.hiEndDate;
    }

    public void setHiEndDate(LocalDate hiEndDate) {
        this.hiEndDate = hiEndDate;
    }

    // toString() Method
    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", nationalID='" + getNationalID() + "'" +
            ", gender='" + isGender() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", homeTown='" + getHomeTown() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", address='" + getAddress() + "'" +
            ", occupation='" + getOccupation() + "'" +
            ", hiCode='" + getHiCode() + "'" +
            ", hiStartDate='" + getHiStartDate() + "'" +
            ", hiEndDate='" + getHiEndDate() + "'" +
            "}";
    }

}
