package com.kmateyev.url.shortener.service.impl;

import com.kmateyev.url.shortener.dto.auth.AuthResponse;
import com.kmateyev.url.shortener.dto.auth.LoginRequest;
import com.kmateyev.url.shortener.dto.auth.RegisterRequest;
import com.kmateyev.url.shortener.entity.user.User;
import com.kmateyev.url.shortener.enums.UserRole;
import com.kmateyev.url.shortener.repository.user.UserRepository;
import com.kmateyev.url.shortener.security.JwtService;
import com.kmateyev.url.shortener.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.generateToken(user));

        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(loginRequest.getUsername()));

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.generateToken(user));

        return authResponse;
    }
}
