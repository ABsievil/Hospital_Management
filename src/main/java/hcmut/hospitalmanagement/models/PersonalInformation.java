package hcmut.hospitalmanagement.models;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private String relative; // người thân
    private String relativePhoneNumber; // sdt người thân
    private String relativeAddress; // địa chỉ người thân
    
    // Bảo hiểm y tế (Health Insurance or HI) 
    private String hiCode;
    private LocalDate hiStartDate;
    private LocalDate hiEndDate;
    
}
