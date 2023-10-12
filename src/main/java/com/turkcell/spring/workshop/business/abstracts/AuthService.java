package com.turkcell.spring.workshop.business.abstracts;

import com.turkcell.spring.workshop.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.spring.workshop.entities.dtos.auth.LoginRequest;
import com.turkcell.spring.workshop.entities.dtos.auth.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}
