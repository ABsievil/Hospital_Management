package hcmut.hospitalmanagement.models;

import jakarta.persistence.Column;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Patient")
public class Patient {

    // Let the database system generate id its self
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "name")
    // private String patientName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    // these are an OBJECT for Patient Management (string for now)
    private String medicalHistory;
    private String testResults;
    private String treatmentSchedule;
    private boolean isActive;
    // private String patientName;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    ImageData image;

    @Embedded
    private PersonalInformation information;

    
    
    // @OneToMany
    // private List<TreatmentHistory> treatmentHistory;

    // Constructor
    

    // We do not need ID in constructor because ID is automatically generated (See above)
}
