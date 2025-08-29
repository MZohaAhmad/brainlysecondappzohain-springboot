package com.secondbrain.service;

import com.secondbrain.dto.LoginRequest;
import com.secondbrain.dto.RegisterRequest;
import com.secondbrain.model.User;
import com.secondbrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;

    public Map<String, Object> register(RegisterRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            response.put("message", "Email already exists");
            return response;
        }
        
        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        User savedUser = userRepository.save(user);
        
        response.put("message", "User successfully created");
        response.put("userId", savedUser.getId());
        
        return response;
    }

    public Map<String, Object> login(LoginRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);
        
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            response.put("message", "Invalid credentials");
            return response;
        }
        
        // Generate JWT token
        String token = jwtService.generateToken(user.getId());
        
        response.put("message", "User logged in successfully");
        response.put("token", token);
        response.put("userID", user.getId());
        
        return response;
    }
}


