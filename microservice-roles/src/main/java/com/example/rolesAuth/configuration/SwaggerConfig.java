package com.example.rolesAuth.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "Api Roles & Permission",
                description = "Application for user registration and login with roles and permissions. Use of Spring Security to access resources",
                termsOfService = "www.robdev.com",
                contact = @Contact(
                        name = "Roberto García",
                        url = "www.robdev.com",
                        email = "robertogarciacr@gmail.com"
                ),
                license = @License(
                        name = "Software Rob License",
                        url = "www.robdev.com",
                        identifier = "1357616-36811386"
                ),
                version = "1.0.0"
        ),
        servers = {
                @Server(
                description = "DEV_SERVER",
                url = "http://localhost:8080"),
                @Server(
                        description = "PROD_SERVER",
                        url = "http://produccion"
                )
        },
        security = @SecurityRequirement(
                name = "Security Token"
        )
)
@SecurityScheme(
        name = "Security Token",
        description = "Access token or my API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {}
