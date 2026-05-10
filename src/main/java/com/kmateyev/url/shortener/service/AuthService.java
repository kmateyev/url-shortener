package com.kmateyev.url.shortener.service;

import com.kmateyev.url.shortener.dto.auth.AuthResponse;
import com.kmateyev.url.shortener.dto.auth.LoginRequest;
import com.kmateyev.url.shortener.dto.auth.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);
}
