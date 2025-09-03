package com.example.Player.Authentication.config;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class Customauthentrypoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException authex)
        throws IOException,ServletException{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"Unauthorized: "+authex.getMessage()+"\"}");
    }

}
