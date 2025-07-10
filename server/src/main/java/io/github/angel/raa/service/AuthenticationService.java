package io.github.angel.raa.service;

import io.github.angel.raa.dto.request.auth.LoginRequest;
import io.github.angel.raa.dto.request.auth.RegisterRequest;
import io.github.angel.raa.dto.response.jwt.JwtResponse;

public interface AuthenticationService {
    JwtResponse login(LoginRequest request);
    JwtResponse register(RegisterRequest request);
    
    

}
