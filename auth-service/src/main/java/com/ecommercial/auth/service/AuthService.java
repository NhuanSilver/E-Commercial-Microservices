package com.ecommercial.auth.service;

import com.ecommercial.auth.dto.RegistrationRequest;
import com.ecommercial.auth.model.User;
import com.ecommercial.auth.repository.UserRepository;
import com.ecommercial.auth.dto.AuthRequest;
import com.ecommercial.auth.dto.AuthResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
            User user = this.userRepository.findUserByUsername(request.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
            String jwtToken = jwtService.generateToken(user);
            return new AuthResponse(jwtToken, user.getUsername(), (List<SimpleGrantedAuthority>) user.getAuthorities());

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid user");
        }
    }

    public AuthResponse register(RegistrationRequest registrationReq) {
        log.info(registrationReq.toString());
        User user = User.builder()
                .username(registrationReq.getUsername())
                .password(passwordEncoder.encode(registrationReq.getPassword()))
                .email(registrationReq.getEmail())
                .authorities(Set.of("ROLE_USER"))
                .build();
        userRepository.save(user);
        return new AuthResponse(user.getUsername(),user.getPassword(), (List<SimpleGrantedAuthority>) user.getAuthorities());
    }
}
