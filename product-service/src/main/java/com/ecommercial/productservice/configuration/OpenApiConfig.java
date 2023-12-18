package com.ecommercial.productservice.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value(value = "http://localhost:8080")
    private String devUrl;

    @Value(value = "https://ecomos-system.herokuapp.com")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("letrungnhan220801@gmail.com");
        contact.setName("Lê Trung Nhân");
        contact.setUrl("https://github.com/LeTrungNhan22");

        License apacheLicense = new License();
        apacheLicense.setName("Apache License Version 2.0");
        apacheLicense.setUrl("https://www.apache.org/licenses/LICENSE-2.0");


        Info info = new Info()
                .title("Product Service API")
                .version("1.0.0")
                .contact(contact)
                .description("This is a Product Service API")
                .termsOfService("http://swagger.io/terms/")
                .license(apacheLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
