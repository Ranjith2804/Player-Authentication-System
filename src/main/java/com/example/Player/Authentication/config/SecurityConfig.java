package com.example.Player.Authentication.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.Player.Authentication.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final Customauthentrypoint customauthentrypoint;
    private final JwtAuthFilter jwtAuthFilter;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    public SecurityConfig(Customauthentrypoint customauthentrypoint, JwtAuthFilter jwtAuthFilter, UserRepository userRepository, UserDetailsService userDetailsService)
    {
        this.customauthentrypoint=customauthentrypoint;
        this.jwtAuthFilter=jwtAuthFilter;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterchain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception-> exception.authenticationEntryPoint(customauthentrypoint))
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

   // @Bean
    /*public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> userRepository.findByUsername(username)
                .map(user-> new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new java.util.ArrayList<>()))
                .orElseThrow(()-> new UsernameNotFoundException("User not Found"));
    }*/

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
        authprovider.setUserDetailsService(userDetailsService);
        authprovider.setPasswordEncoder(passwordEncoder());
        return authprovider;
    }
   /* @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        new java.util.ArrayList<>()))
                .orElseThrow(()-> new UsernameNotFoundException("User not Found"));
    }*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
