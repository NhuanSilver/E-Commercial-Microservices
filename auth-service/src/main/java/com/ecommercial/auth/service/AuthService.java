package com.ecommercial.auth.service;

import com.ecommercial.auth.dto.RegistrationRequest;
import com.ecommercial.auth.enums.Role;
import com.ecommercial.auth.model.User;
import com.ecommercial.auth.repository.UserRepository;
import com.ecommercial.auth.dto.AuthRequest;
import com.ecommercial.auth.dto.AuthResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Something went wrong");
        }
        User user = this.userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        String role = ((List<SimpleGrantedAuthority>) user.getAuthorities()).get(0).getAuthority() ;
        HashMap<String, Object> clams = new HashMap<>();
        clams.put("role", role);
        String jwtToken = jwtService.generateToken(clams, user);

        return AuthResponse.builder()
                .username(user.getUsername())
                .token(jwtToken)
                .role(role)
                .build();
    }

    public AuthResponse register(RegistrationRequest registrationReq) {
        log.info(registrationReq.toString());
        if (this.userRepository.findUserByUsername(registrationReq.getUsername()).isPresent()) {
            throw new IllegalArgumentException("user existed");
        }
        User user = User.builder()
                .username(registrationReq.getUsername())
                .password(passwordEncoder.encode(registrationReq.getPassword()))
                .email(registrationReq.getEmail())
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        String role = ((List<SimpleGrantedAuthority>) user.getAuthorities()).get(0).getAuthority() ;

        HashMap<String, Object> clams = new HashMap<>();
        clams.put("role", role);
        String jwtToken = jwtService.generateToken(clams, user);

        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(role)
                .build();
    }
}
