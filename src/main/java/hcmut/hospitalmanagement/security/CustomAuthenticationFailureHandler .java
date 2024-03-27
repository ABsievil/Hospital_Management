// package hcmut.hospitalmanagement.security;

// import org.springframework.security.web.authentication.AuthenticationFailureHandler;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;

// import javax.naming.AuthenticationException;

// import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;

// public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
//     public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
//                                         HttpServletResponse httpServletResponse
//         , AuthenticationException e) throws IOException {
        
//         httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

//         String jsonPayload = "{\"message\" : \"%s\", \"timestamp\" : \"%s\" }";
//         httpServletResponse.getOutputStream()
//             .println(String.format(jsonPayload,
//                 e.getMessage(),
//                 Calendar.getInstance().getTime()));
//     }
// }
