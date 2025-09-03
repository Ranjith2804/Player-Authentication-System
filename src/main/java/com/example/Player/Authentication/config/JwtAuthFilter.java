package com.example.Player.Authentication.config;
import com.example.Player.Authentication.service.JwtServvice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
        private JwtServvice jwtServvice;
        private UserDetailsService userDetailsService;

        public JwtAuthFilter(JwtServvice jwtServvice,UserDetailsService userDetailsService){
            this.jwtServvice=jwtServvice;
            this.userDetailsService=userDetailsService;
        }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authheader=request.getHeader("Authorization");
        String token=null;
        String name=null;
        if(authheader != null && authheader.startsWith("Bearer ")){
            token=authheader.substring(7);
            name=jwtServvice.extractUsername(token);
        }
        if(name != null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
                UserDetails userDetails=userDetailsService.loadUserByUsername(name);
                if(jwtServvice.validatetoken(token,userDetails)){
                    UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authtoken);
                }
        }
        filterChain.doFilter(request,response);
    }



}
