// package hcmut.hospitalmanagement.security;

// import java.util.Collection;
// import java.util.Collections;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import hcmut.hospitalmanagement.models.Doctor;
// import hcmut.hospitalmanagement.models.Patient;
// import lombok.AllArgsConstructor;
// import lombok.Data;

// @Data
// @AllArgsConstructor
// public class CustomUserDetails implements UserDetails {
//     private Patient patient;
//     private Doctor doctor;
//     boolean isPatient;

//     public CustomUserDetails(Patient pa){
//         this.patient = pa;
//         this.doctor = null;
//         isPatient = true;
//     }
//     public CustomUserDetails(Doctor doc){
//         this.doctor = doc;
//         this.patient = null;
//         isPatient = false;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return isPatient 
//         ? Collections.singleton(new SimpleGrantedAuthority("PATIENT"))
//         : Collections.singleton(new SimpleGrantedAuthority("DOCTOR"));
//     }

//     @Override
//     public String getPassword() {
//         if (patient != null) {
//             return patient.getPassword();
//         } else {
//             return doctor.getPassword();
//         }
//     }

//     @Override
//     public String getUsername() {
//         if (patient != null) {
//             return patient.getUsername();
//         } else {
//             return doctor.getUsername();
//         }
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
// }
