package com.example.springbootclient.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 *
 * @author ishani.s
 */
@Getter
@Setter
public class CustomerCreateRequest {
    @NotBlank
    @Size(max = 120)
    private String name;

    @Email
    @Size(max = 180)
    private String email;

    @Size(max = 30)
    private String phone;
}
