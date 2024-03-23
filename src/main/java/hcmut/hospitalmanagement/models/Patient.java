package hcmut.hospitalmanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Patient {

    // Let the database system generate id its self
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String patientName;

    // these are an OBJECT for Patient Management (string for now)
    private String medicalHistory;
    private String testResults;
    private String treatmentSchedule;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    // Constructor
    public Patient() {}

    // We do not need ID in constructor because ID is automatically generated (See above)

    public Patient(String patientName, String medicalHistory, String testResults, String treatmentSchedule) {
        this.patientName = patientName;
        this.medicalHistory = medicalHistory;
        this.testResults = testResults;
        this.treatmentSchedule = treatmentSchedule;
    }
    
    // Getters and Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMedicalHistory() {
        return this.medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getTestResults() {
        return this.testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getTreatmentSchedule() {
        return this.treatmentSchedule;
    }

    public void setTreatmentSchedule(String treatmentSchedule) {
        this.treatmentSchedule = treatmentSchedule;
    }
    
    
    // toString() Method to convert data to .JSON file
<<<<<<< HEAD


=======
>>>>>>> a491d596bf33b1a0661a2754459147cf5674dd2c
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", patientName='" + getPatientName() + "'" +
            ", medicalHistory='" + getMedicalHistory() + "'" +
            ", testResults='" + getTestResults() + "'" +
            ", treatmentSchedule='" + getTreatmentSchedule() + "'" +
            "}";
    }
    

}
