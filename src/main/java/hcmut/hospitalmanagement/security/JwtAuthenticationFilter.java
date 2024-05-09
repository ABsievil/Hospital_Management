package hcmut.hospitalmanagement.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component    // conflict between Bean(private final) and Autowired
//@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // bộ lọc sử dụng cho các request từ phía FE khi đã có jwt token rồi
    @Autowired  //autowired ? what happend?
    private JwtUtilities jwtUtilities;

    @Autowired  //autowired ? what happend?
    private CustomUserDetailsService customUserDetailsService;

    // check exception occur for controller method
    @Autowired
    private AuthenticationBean authenticationBean;

    // @Autowired
    // private WhiteList whiteList;

    // private String[] WHITE_LIST_URL = {
    //     "/",
    //     "/index",
    //     "/about",
    //     "/login",
    //     "/403",
    //     "/api/authenticate"
    // };

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, 
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // nếu login lần đầu tiên, thì request gửi tới ko được chứa /api/authenticate
        // duyệt qua các path có thể bỏ qua lớp jwt filter
        // for (String url : WHITE_LIST_URL) {
        //     if (request.getServletPath().contains(url)) {
        //         filterChain.doFilter(request, response);
        //         return;
        //     }
        // }

        if(request.getServletPath().contains("/api/authenticate")){ 
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = jwtUtilities.getToken(request);
            
            if(token != null && jwtUtilities.validateToken(token)){
                String uname = jwtUtilities.extractUsername(token);
                
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(uname);
                if(userDetails != null){
                    UsernamePasswordAuthenticationToken authenticationToken = 
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                    
                    log.info("authenticated user with username :{}", uname);
                    // set user vào SecurityContextHolder
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    // Set authentication vào AuthenticationBean
                    authenticationBean.setAuthentication(authenticationToken);
                }
            }
         } catch (Exception ex) {
             log.error("failed on set user authentication");
         }

        filterChain.doFilter(request, response);
    }
}
