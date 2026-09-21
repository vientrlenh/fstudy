package com.vsoft.fstudy.feature.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.fstudy.shared.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {
    
    @GetMapping
    public ResponseEntity<ApiResponse<String>> getHealth() {
        return ResponseEntity.ok(ApiResponse.success("SERVER_HEALTHY", "Server ok"));
    }
}
