package hcmut.hospitalmanagement.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Patient {

    // Let the database system generate id its self
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean active;
    // private String patientName;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    ImageData image;

    @Embedded
    private PersonalInformation information;

    @ManyToMany(mappedBy = "patients", cascade = CascadeType.ALL)
    private List<Doctor> doctors;
    
    @OneToMany
    private List<TreatmentHistory> treatmentHistory;

    // Constructor
    public Patient() {}

    // We do not need ID in constructor because ID is automatically generated (See above)

    public Patient(boolean active, ImageData image, PersonalInformation information, List<Doctor> doctors, List<TreatmentHistory> treatmentHistory) {
        this.active = active;
        this.image = image;
        this.information = information;
        this.doctors = doctors;
        this.treatmentHistory = treatmentHistory;
    }
    
    
    
    // Getters and Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public List<Doctor> getDoctors() {
        return this.doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<TreatmentHistory> getTreatmentHistory() {
        return this.treatmentHistory;
    }

    public void setTreatmentHistory(List<TreatmentHistory> treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
    }

    // toString() Method to convert data to .JSON file

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", active='" + isActive() + "'" +
            ", image='" + getImage() + "'" +
            ", information='" + getInformation() + "'" +
            ", doctors='" + getDoctors() + "'" +
            ", treatmentHistory='" + getTreatmentHistory() + "'" +
            "}";
    }
    

}
