package com.eodya.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {
    private final Environment env;

    @GetMapping("/health")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok(("Hello gam Server!"));
    }
}