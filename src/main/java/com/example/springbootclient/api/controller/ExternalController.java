package com.example.springbootclient.api.controller;

import com.example.springbootclient.integration.ExternalPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * @author ishani.s
 */
@RestController
@RequestMapping("/api/external")
public class ExternalController {
    private final ExternalPostService externalPostService;

    public ExternalController(ExternalPostService externalPostService) {
        this.externalPostService = externalPostService;
    }

    // Client -> Application API -> Third-party API
    @GetMapping("/posts/{id}")
    public ResponseEntity<Map<String, Object>> getThirdPartyPost(@PathVariable long id) {
        return ResponseEntity.ok(externalPostService.fetchPostById(id));
    }
}