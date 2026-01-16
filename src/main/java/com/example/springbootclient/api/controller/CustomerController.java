package com.example.springbootclient.api.controller;

import com.example.springbootclient.api.dto.CustomerCreateRequest;
import com.example.springbootclient.api.dto.CustomerResponse;
import com.example.springbootclient.api.dto.CustomerUpdateRequest;
import com.example.springbootclient.domain.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ishani.s
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerCreateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Pagination: 10 records per page (fixed)
    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> list(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(service.listPaged(page));
    }
}