package com.example.springbootclient.domain.repository;

import com.example.springbootclient.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ishani.s
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
