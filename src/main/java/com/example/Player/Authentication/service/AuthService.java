package com.example.Player.Authentication.service;
import com.example.Player.Authentication.dto.AuthResponse;
import com.example.Player.Authentication.dto.RegisterRequest;
import com.example.Player.Authentication.model.user;
import com.example.Player.Authentication.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.example.Player.Authentication.dto.LoginRequest;
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServvice jwtservice;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtServvice jwtservice,AuthenticationManager authenticationManager){
        this.userRepository= userRepository;
        this.passwordEncoder =passwordEncoder;
        this.jwtservice=jwtservice;
        this.authenticationManager=authenticationManager;
    }

    public user register(RegisterRequest Request){
        if(userRepository.findByUsername(Request.username()).isPresent()){
            throw new IllegalStateException("Username already exists");
        }

        user newuser = new user();
        newuser.setUsername(Request.username());
        newuser.setPassword(passwordEncoder.encode(Request.password()));
        return userRepository.save(newuser);
    }
    public String login(LoginRequest request){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()));
        if(authentication.isAuthenticated()){
            return jwtservice.generatetoken(request.username());
        }
        else{
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
}
