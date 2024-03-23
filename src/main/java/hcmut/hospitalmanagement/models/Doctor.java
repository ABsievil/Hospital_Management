package hcmut.hospitalmanagement.models;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean active;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    ImageData image;

    @Embedded
    private PersonalInformation information;

    // Table to store the relation between doctors and patients
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "doctor_patient",
        joinColumns = @JoinColumn(name = "doctor_id"),
        inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patients;

    // Constructors
    public Doctor() {
    }

    public Doctor(boolean active, ImageData image, PersonalInformation information, List<Patient> patients) {
        this.active = active;
        this.image = image;
        this.information = information;
        this.patients = patients;
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

    public List<Patient> getPatients() {
        return this.patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }



    // toString() Method to convert to JSON

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", active='" + isActive() + "'" +
            ", image='" + getImage() + "'" +
            ", information='" + getInformation() + "'" +
            ", patients='" + getPatients() + "'" +
            "}";
    }
    
    
}
