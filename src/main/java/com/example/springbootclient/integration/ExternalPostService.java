package com.example.springbootclient.integration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

/**
 *
 * @author ishani.s
 */
@Service
public class ExternalPostService {
    private final WebClient webClient;

    public ExternalPostService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    public Map<String, Object> fetchPostById(long id) {
        // Nested call to a 3rd-party public API
        Mono<Map> mono = webClient.get()
                .uri("/posts/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofSeconds(10));

        return mono.block();
    }
}