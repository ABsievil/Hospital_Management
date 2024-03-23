package hcmut.hospitalmanagement.models;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean isWorking;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    ImageData image;

    
    @Embedded
    private PersonalInformation information;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedule;

    // Constructors
    public Doctor() {
    }



    public Doctor(Long id, boolean isWorking, ImageData image, PersonalInformation information, List<Patient> patients, List<Schedule> schedule) {
        this.id = id;
        this.isWorking = isWorking;
        this.image = image;
        this.information = information;
        this.patients = patients;
        this.schedule = schedule;
    }
    
    

    // Getters and Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIsWorking() {
        return this.isWorking;
    }

    public boolean getIsWorking() {
        return this.isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public ImageData getImage() {
        return this.image;
    }

    public void setImage(ImageData image) {
        this.image = image;
    }

    public PersonalInformation getInformation() {
        return this.information;
    }

    public void setInformation(PersonalInformation information) {
        this.information = information;
    }

    public List<Patient> getPatients() {
        return this.patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Schedule> getSchedule() {
        return this.schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

   

    // toString() Method to convert to JSON

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", isWorking='" + isIsWorking() + "'" +
            ", image='" + getImage() + "'" +
            ", information='" + getInformation() + "'" +
            ", patients='" + getPatients() + "'" +
            ", schedule='" + getSchedule() + "'" +
            "}";
    }
    
}
