package hcmut.hospitalmanagement.models;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isActive;
    private String specialty; // chuyên ngành
    private String position; // chức vụ
    private String degree; // học vị
    private String academicRank; // học hàm
    private String trainingPlace; // nơi đào tạo
    
    @Column(unique = true)
    private String personalCode;
    
    @Embedded
    private PersonalInformation information;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, unique = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
