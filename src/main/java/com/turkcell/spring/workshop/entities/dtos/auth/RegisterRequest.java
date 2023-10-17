package com.turkcell.spring.workshop.entities.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String roleName;
}
