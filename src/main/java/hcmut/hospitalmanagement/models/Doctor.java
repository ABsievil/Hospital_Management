package hcmut.hospitalmanagement.models;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isActive;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

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


    public Doctor( boolean isWorking, String username, String password, ImageData image, PersonalInformation information, List<Patient> patients) {
        this.isActive = isWorking;
        this.username = username;
        this.password = password;
        this.image = image;
        this.information = information;
        this.patients = patients;
    }

    


    
    

}
