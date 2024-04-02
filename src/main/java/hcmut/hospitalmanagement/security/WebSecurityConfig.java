package hcmut.hospitalmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    // @Bean
    // AuthenticationSuccessHandler authenticationSuccessHandler() {
    //     return new CustomAuthenticationSuccessHandler();
    // }

    // @Bean
    // AuthenticationFailureHandler authenticationFailureHandler() {
    //     return new CustomAuthenticationFailureHandler();
    // }
    //demo in db
    @Autowired
    CustomUserDetailsService userService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // encrypt password of user
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // use method passwordEncoder
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    //demo in-memory
    // @Bean
    //  public UserDetailsService userDetailsService() {
    //      UserDetails user = User.builder()
    //          .username("user")
    //          .password(passwordEncoder().encode("1234"))
    //          .roles("USER")
    //          .build();
    //      UserDetails admin = User.builder()
    //          .username("admin")
    //          .password(passwordEncoder().encode("1245"))
    //          .roles("ADMIN", "USER")
    //          .build();
    //      return new InMemoryUserDetailsManager(user, admin);
    //  }
     
     //note : role in db current is: PATIENT and DOCTOR
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/", "/index").permitAll() 
                .requestMatchers("/helloUser").hasAnyRole("USER", "ADMIN") 
                .requestMatchers("/helloAdmin").hasRole("ADMIN") 
                .anyRequest().authenticated() 
            )
            .formLogin( formLogin -> formLogin
                .loginPage("/login")           // you must custom file login.html, defaul is login page of spring
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                // .successHandler(authenticationSuccessHandler())
                // .failureHandler(authenticationFailureHandler())
                .permitAll()
            )
            .logout(logout -> logout
                //.logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );
            //.csrf(csrf -> csrf.disable());   // what is csrf, should disable it ?
        
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //HTTP requirements to the resources in these links do not have to undergo security filters provided by Spring Security
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/css/**", "/images/**", "/js/**");
    }
}

