package hcmut.hospitalmanagement.models;




import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Patient {

    // Let the database system generate id its self
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    private PersonalInformation information;
    
    String room;
    
}
