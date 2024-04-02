package hcmut.hospitalmanagement.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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


    // @OneToMany
    // private Doctor doctor;
    
}
