package com.example.springbootclient.api.dto;

import lombok.*;

/**
 *
 * @author ishani.s
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
