package com.ecommercial.cart.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Nhuan",
                        email = "nhuan.nhp@gmail.com"
                ),
                description = "OpenApi documentation for cart service",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local env",
                        url = "http://localhost:8082"
                )
        }
)
public class OpenApiConfig {

}
