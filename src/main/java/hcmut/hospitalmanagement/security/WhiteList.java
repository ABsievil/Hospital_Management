package hcmut.hospitalmanagement.security;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhiteList {
    private String[] WHITE_LIST_URL = {
        "/",
        "/index",
        "/about",
        "/login",
        "/403",
        "/api/authenticate"
    };

    // public String[] get_WHITE_LIST_URL(){
    //     return WHITE_LIST_URL;
    // }
}
