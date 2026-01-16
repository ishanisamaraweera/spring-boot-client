package com.example.springbootclient.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 * @author ishani.s
 */
@RestController
public class HealthController {
    @GetMapping("/api/health")
    public Map<String, Object> health() {
        return Map.of("status", "UP");
    }
}
