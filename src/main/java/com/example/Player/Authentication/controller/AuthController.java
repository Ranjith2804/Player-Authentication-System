package com.example.Player.Authentication.controller;

import com.example.Player.Authentication.dto.RegisterRequest;
import com.example.Player.Authentication.dto.AuthResponse;
import com.example.Player.Authentication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Player.Authentication.dto.LoginRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController (AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        try{
            authService.register(request);
            return ResponseEntity.ok(new AuthResponse("User Registered Succesfully!"));
        }catch(IllegalStateException e){
            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        try{
            String token=authService.login(request);
            return ResponseEntity.ok(new AuthResponse("Login Done!",token));
        }catch(UsernameNotFoundException e){
            return ResponseEntity.status(401).body(new AuthResponse("Invalid username or password"));
        }
    }
}
