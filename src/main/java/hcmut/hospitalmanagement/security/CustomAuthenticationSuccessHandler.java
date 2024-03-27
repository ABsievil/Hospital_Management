// package hcmut.hospitalmanagement.security;

// import java.io.IOException;

// import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//     public void  onAuthenticationSuccess(HttpServletRequest request,
//                                          HttpServletResponse response,
//                                          Authentication authentication ) throws IOException, ServletException {
//         System.out.println("Logged user: " + authentication.getUsername());
//         response.sendRedirect("/");
//     }
// }
