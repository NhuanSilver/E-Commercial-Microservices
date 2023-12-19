package com.ecommercial.apigateway.filter;

import com.ecommercial.apigateway.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final JwtUtils jwtService;

    public AuthenticationFilter(JwtUtils jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if(isSecured.test(request)){
                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorization header");
                }

                final String authHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    final String token = authHeader.substring(7);
                    if(!jwtService.isTokenValid(token)){
                        throw new RuntimeException("Invalid token");
                    }
                    this.populateRequestWithHeaders(exchange, token);
                }
            }
            return chain.filter(exchange);
        });
    }


    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtService.extractAllClaims(token);
        log.info("populate");
        exchange.getRequest().mutate()
                .header("username", String.valueOf(jwtService.extractUsername(token)))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }

    public final List<String> endpoints = List.of(
            "/auth/register",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> endpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));


    public static class Config {

    }
}
