package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.AuthService;
import com.turkcell.spring.workshop.core.jwt.JwtService;
import com.turkcell.spring.workshop.entities.Role;
import com.turkcell.spring.workshop.entities.User;
import com.turkcell.spring.workshop.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.spring.workshop.entities.dtos.auth.LoginRequest;
import com.turkcell.spring.workshop.entities.dtos.auth.RegisterRequest;
import com.turkcell.spring.workshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;//giriş yapmış ve kayıtlı bir kullanıcıya jwt döneceğiz
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        //validasyon ve iş kuralları daha sonra eklenecek
        User user = User.builder() //rolünü de buradan atayacağız
                .name(request.getName())
                .lastName(request.getLastName())
                .role(Role.builder().id(request.getRole()).build())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
