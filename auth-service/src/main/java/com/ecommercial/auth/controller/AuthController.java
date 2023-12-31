package com.ecommercial.auth.controller;

import com.ecommercial.auth.service.AuthService;
import com.ecommercial.auth.dto.RegistrationRequest;
import com.ecommercial.auth.dto.AuthRequest;
import com.ecommercial.auth.dto.AuthResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegistrationRequest registrationReq) {
        return this.authService.register(registrationReq);
    }
}
