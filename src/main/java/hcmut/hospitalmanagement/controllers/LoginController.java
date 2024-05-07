package hcmut.hospitalmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hcmut.hospitalmanagement.models.Employee;
import hcmut.hospitalmanagement.models.ResponseObject;
import hcmut.hospitalmanagement.payload.LoginRequest;
import hcmut.hospitalmanagement.repositories.EmployeeRepository;
import hcmut.hospitalmanagement.security.AuthenticationBean;
import hcmut.hospitalmanagement.security.CustomUserDetails;
import hcmut.hospitalmanagement.security.JwtUtilities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

@RestController
@RequestMapping("/api")
public class LoginController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtilities jwtUtilities;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private AuthenticationBean authenticationBean;

    private final Logger logger = LoggerFactory.getLogger(this.getClass()); // debug feature

    // check request from login
    @PostMapping("/authenticate")
    public ResponseEntity<ResponseObject> authenticate( @RequestBody LoginRequest loginRequest) {
        try {
                // Xác thực thông tin người dùng Request lên
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
            );

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            Employee employee = employeeRepository.findByUsername(authentication.getName());
            if(employee == null){
                throw new UsernameNotFoundException("User not found! ");
            }
            // Trả về token cho người dùng.
            String role = employee.getRole().toString();
            String token = jwtUtilities.generateToken(employee.getUsername(), role);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("OK", "Query login to get jwt token successfully", token));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("ERROR" + ", " + e.getMessage().toString(), "Error login to get jwt token failed", loginRequest));
        }
    }

    // check other request
    // note: the requests not call api: /api/checkJwt is still class JwtAuthentication Filter, so you mush add
    // request path to ignore filter of JwtAuthentication Filter 
    @GetMapping("/checkJwt")
    public ResponseEntity<ResponseObject> checkToken () {
        try {
            // B1: call JWT Authentication Filter

            //B2: check if error occur then return "Error authenticate by jwt token failed"
            UsernamePasswordAuthenticationToken auth = authenticationBean.getAuthentication();
            authenticationBean.setAuthentication(null);  //phải xóa ko là hẹo
            if (auth== null) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY)    
                .body(new ResponseObject("ERROR", "Error authenticate by jwt token failed", null));
            }
            
            // B3: create new token and return to user
            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // SecurityContextHolder đã được set user ở JwtAuthenticationFilter
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            Employee employee = employeeRepository.findByUsername(authentication.getName());
            if(employee == null){
                throw new UsernameNotFoundException("User not found! ");
            }
            // Trả về token cho người dùng.
            String role = employee.getRole().toString();
            String token = jwtUtilities.generateToken(employee.getUsername(), role);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("OK", "Query to get jwt token successfully", token));

        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("ERROR" + ", " + e.getMessage().toString(), "Error to get jwt token failed", null));
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JwtToken {
        private String token;
    }
    /* TEST METHOD */
    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public RandomStuff randomStuff(){
        try {
            logger.error("calling to randomStuff func");
            return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
        } catch(DataAccessException e) {
            return new RandomStuff("failing to call randomStuff func");
        }
    }

    @Data
    @AllArgsConstructor
    public static class RandomStuff {
        private String message;
    }

    @GetMapping("/test")
    public String testAuthentication() {
        UsernamePasswordAuthenticationToken authentication = authenticationBean.getAuthentication();
        if (authentication != null) {
            authenticationBean.setAuthentication(null);  //phải xóa ko là hẹo
            return "Authenticated user: " + authentication.getName();
        } else {
            authenticationBean.setAuthentication(null);
            return "Un-authenticated user";
        }
    }
}


/*
 * getmapping in PageController.java not comment yet
 * link login in WebSecurityConfig.java connect with this controller not linking yet
 * /payload is loda's code
 * you need to code in https://github.dev/Ons-diweni/Spring-Security-6-JWT to finish proj
 * security jwt folder is finished
 * Not yet debugging
 * generateToken func in jwtUtilities.java is reconfigured
 * db in cloud is fixed by delete name property
 */