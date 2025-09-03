package com.example.Player.Authentication.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import java.security.Principal;
@RestController
@RequestMapping("/api/data")
public class DataController {
    @GetMapping("/me")
    public ResponseEntity<String> getMydata(Principal principal){
        String username=principal.getName();
        return ResponseEntity.ok("This is protected data for:"+username);
    }
}
