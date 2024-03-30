package hcmut.hospitalmanagement.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TreatmentHistory {
    private String disease;
    private Date admissionDate;
    private Date dischargeDate;
    private double cost;
    private String medicationList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doctor doctor;

    public TreatmentHistory() {
    }

    public TreatmentHistory(String disease, Date admissionDate, Date dischargeDate, double cost, String medicationList, Doctor doctor) {
        this.disease = disease;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.cost = cost;
        this.medicationList = medicationList;
        this.doctor = doctor;
    }

    public String getDisease() {
        return this.disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getAdmissionDate() {
        return this.admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return this.dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getMedicationList() {
        return this.medicationList;
    }

    public void setMedicationList(String medicationList) {
        this.medicationList = medicationList;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "{" +
            " disease='" + getDisease() + "'" +
            ", admissionDate='" + getAdmissionDate() + "'" +
            ", dischargeDate='" + getDischargeDate() + "'" +
            ", cost='" + getCost() + "'" +
            ", medicationList='" + getMedicationList() + "'" +
            ", doctor='" + getDoctor() + "'" +
            "}";
    }
    
}
