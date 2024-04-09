package hcmut.hospitalmanagement.models;

import java.time.LocalDate;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TreatmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // bệnh nhân
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;

    // bác sĩ phụ trách
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;

    private String disease; // loại bệnh
    private LocalDate admissionDate; // ngày nhập viện
    private LocalDate dischargeDate; // ngày xuất viện
    private double cost; // chi phí

    @Transient
    private Map<String, Integer> medicationList; // đơn  thuốc
    
    @Enumerated(EnumType.STRING)
    private Rate rate; // đánh giá của bệnh nhân
}
