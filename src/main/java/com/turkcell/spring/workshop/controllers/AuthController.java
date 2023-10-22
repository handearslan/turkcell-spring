package com.turkcell.spring.workshop.controllers;


import com.turkcell.spring.workshop.business.abstracts.AuthService;
import com.turkcell.spring.workshop.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.spring.workshop.entities.dtos.auth.LoginRequest;
import com.turkcell.spring.workshop.entities.dtos.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("login")
    public AuthenticationResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request){
        return  authService.register(request);
    }

}
