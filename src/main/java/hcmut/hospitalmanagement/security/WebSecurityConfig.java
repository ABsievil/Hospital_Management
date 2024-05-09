package hcmut.hospitalmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

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

    /* SEC */
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
    }

    @Bean
    //@Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }


    //demo in db
    @Autowired
    CustomUserDetailsService userService;

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // encrypt password of user
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // use method passwordEncoder
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
     // cho permit all hết để jwt filter lọc thoi
    // private static final String[] WHITE_LIST_URL = {
    //     "/",
    //     "/index",
    //     "/about",
    //     "/login",
    //     "/403",
    //     "/api/authenticate",
    //     "api/test"
    // };


     //note : role in db current is: PATIENT and DOCTOR
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // .authorizeHttpRequests((authorize) -> authorize
            //     .requestMatchers("/", "/index", "/about").permitAll() 
            //     .requestMatchers("/add/**", "/report").hasAuthority("ADMIN")
            //     .requestMatchers("/patientlist/patientinfor/**").hasAnyAuthority("ADMIN", "DOCTOR", "NURSE")
            //     .anyRequest().authenticated() 
            // )
            // .formLogin( formLogin -> formLogin
            //     .loginPage("/login")           // you must custom file login.html, defaul is login page of spring
            //     .loginProcessingUrl("/process-login")
            //     .defaultSuccessUrl("/home")
            //     .failureUrl("/login?error=true")
            //     .successHandler(customAuthenticationSuccessHandler)
            //     // .failureHandler(authenticationFailureHandler())
            //     .permitAll()
            // )
            // .logout(logout -> logout
            //     //.logoutUrl("/logout")
            //     .logoutSuccessUrl("/index")
            //      .invalidateHttpSession(true)
            //      .deleteCookies("JSESSIONID")
            //     .permitAll()
            // )
            // .rememberMe(remember -> remember
            //     .key("4Bsi3vil")
            //     .tokenValiditySeconds(10)) // 1 * 24 * 60 * 60 (s) = 24h  //current is not working
            // .exceptionHandling(exeption -> exeption.accessDeniedPage("/403"))
            .csrf(csrf -> csrf.disable())   // what is csrf, should disable it ?
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            
            http.authenticationProvider(authenticationProvider());
            http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);    

        
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //HTTP requirements to the resources in these links do not have to undergo security filters provided by Spring Security
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/css/**", "/images/**", "/js/**");
    }
}

