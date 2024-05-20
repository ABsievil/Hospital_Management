package hcmut.hospitalmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsernameDTO {
    String oldUsername;
    String newUsername;
    String pwd;
}
