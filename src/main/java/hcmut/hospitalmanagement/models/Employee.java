package hcmut.hospitalmanagement.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    // private ImageData image;
    private String specialty;
    private PersonalInformation information;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

<<<<<<< HEAD
    @Column(name = "password", nullable = false, unique = true) //please set unique = false after renew db
=======
    @Column(name = "password", nullable = false, unique = false)
>>>>>>> 98415ebc1a132785fdb0e85f4831d07e708c3ea3
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    
}
