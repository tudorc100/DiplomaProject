package com.medical.demo.security.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SignupRequest {
    private String username;

    private String name;
    private String cnp;
    private String email;
    private String password;
    private Set<String> roles;
}
