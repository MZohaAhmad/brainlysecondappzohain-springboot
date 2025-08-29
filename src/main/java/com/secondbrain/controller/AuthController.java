package com.secondbrain.controller;

import com.secondbrain.dto.LoginRequest;
import com.secondbrain.dto.RegisterRequest;
import com.secondbrain.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "${cors.allowed-origin}", allowCredentials = "true")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequest request) {
        Map<String, Object> response = authService.register(request);
        
        if (response.containsKey("message") && 
            response.get("message").equals("Email already exists")) {
            return ResponseEntity.status(409).body(response);
        }
        
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request) {
        Map<String, Object> response = authService.login(request);
        
        if (response.containsKey("message") && 
            response.get("message").equals("Invalid credentials")) {
            return ResponseEntity.status(401).body(response);
        }
        
        return ResponseEntity.ok(response);
    }
}


