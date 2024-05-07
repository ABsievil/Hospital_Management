package hcmut.hospitalmanagement.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationBean {
    private UsernamePasswordAuthenticationToken authentication;

    public UsernamePasswordAuthenticationToken getAuthentication() {
        return authentication;
    }

    public void setAuthentication(UsernamePasswordAuthenticationToken authentication) {
        this.authentication = authentication;
    }
}
