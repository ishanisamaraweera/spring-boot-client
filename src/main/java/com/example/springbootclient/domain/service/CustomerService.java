package com.example.springbootclient.domain.service;

import com.example.springbootclient.api.dto.CustomerCreateRequest;
import com.example.springbootclient.api.dto.CustomerResponse;
import com.example.springbootclient.api.dto.CustomerUpdateRequest;
import com.example.springbootclient.common.exception.NotFoundException;
import com.example.springbootclient.domain.entity.Customer;
import com.example.springbootclient.domain.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ishani.s
 */
@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = false)
    public CustomerResponse create(CustomerCreateRequest req) {
        Customer c = new Customer();
        c.setName(req.getName());
        c.setEmail(req.getEmail());
        c.setPhone(req.getPhone());
        Customer saved = repository.save(c);
        return toResponse(saved);
    }

    @Transactional(readOnly = false)
    public CustomerResponse update(Long id, CustomerUpdateRequest req) {
        Customer c = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + id));
        c.setName(req.getName());
        c.setEmail(req.getEmail());
        c.setPhone(req.getPhone());
        Customer saved = repository.save(c);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public CustomerResponse getById(Long id) {
        Customer c = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + id));
        return toResponse(c);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponse> listPaged(int page) {
        Pageable pageable = PageRequest.of(page, 10); // 10 records per page
        return repository.findAll(pageable).map(this::toResponse);
    }

    private CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(c.getId(), c.getName(), c.getEmail(), c.getPhone());
    }
}