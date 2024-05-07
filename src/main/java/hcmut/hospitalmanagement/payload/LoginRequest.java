package hcmut.hospitalmanagement.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
//import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    private String username;

    private String password;
}
