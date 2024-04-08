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
    private String name;    // include first name and middle name
    private String Lname;  // last name of user
    private String nationalID;
    private boolean gender;
    private String ethnic; // dân tộc
    private String religion; // tôn giáo
    private LocalDate dateOfBirth;
    private String homeTown;
    private String phoneNumber;
    private String address;
    private String email;
    private String occupation;
    private String personelCode;

    private String relative; // người thân
    private String relationWithRelative; // quan hệ với người thân
    private String relativeOccupation; // nghề nghiệp người thân
    private String relativePhoneNumber1; // sdt người thân 
    private String relativePhoneNumber2; // sdt khác của người thân
    private String relativeAddress; // địa chỉ người thân
    
    // Bảo hiểm y tế (Health Insurance or HI) 
    private String hiCode;
    private LocalDate hiStartDate;
    private LocalDate hiEndDate;
    
}
