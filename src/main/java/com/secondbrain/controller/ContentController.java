package com.secondbrain.controller;

import com.secondbrain.dto.ContentRequest;
import com.secondbrain.model.Content;
import com.secondbrain.service.ContentService;
import com.secondbrain.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "${cors.allowed-origin}", allowCredentials = "true")
public class ContentController {
    
    @Autowired
    private ContentService contentService;
    
    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUserContent(@RequestHeader("token") String token) {
        try {
            String userId = jwtService.extractUserId(token);
            List<Content> contents = contentService.getUserContent(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("data", contents);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid or expired token");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/addcontent")
    public ResponseEntity<Map<String, Object>> createContent(
            @Valid @RequestBody ContentRequest request,
            @RequestHeader("token") String token) {
        try {
            String userId = jwtService.extractUserId(token);
            Content content = contentService.createContent(request, userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Content added successfully");
            response.put("content", content);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Failed to add content");
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<Map<String, Object>> deleteContent(
            @PathVariable String title,
            @RequestHeader("token") String token) {
        try {
            String userId = jwtService.extractUserId(token);
            contentService.deleteContent(title, userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Content deleted successfully");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Failed to delete content");
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/share/{userId}")
    public ResponseEntity<Map<String, Object>> getSharedContent(
            @PathVariable String userId,
            @RequestHeader("token") String token) {
        try {
            // Validate the requesting user's token
            jwtService.extractUserId(token);
            
            List<Content> contents = contentService.getSharedContent(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("data", contents);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid or expired token");
            return ResponseEntity.status(401).body(response);
        }
    }
}


