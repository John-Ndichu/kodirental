package com.johnndichu.kodirental.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnndichu.kodirental.dtos.ContributorResponse;
import com.johnndichu.kodirental.dtos.LoginRequest;
import com.johnndichu.kodirental.dtos.LoginResponse;
import com.johnndichu.kodirental.dtos.RegisterContributorRequest;
import com.johnndichu.kodirental.service.AuthService;

@Tag(name = "Auth", description = "Contributor registration and login")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Register a new contributor",
            description = "Creates a lightweight contributor account (name, email, password). " +
                    "No email verification required."
    )
    @SecurityRequirements
    @PostMapping("/register")
    public ContributorResponse register(@Valid @RequestBody RegisterContributorRequest request) {
        return authService.register(request);
    }

    @Operation(
            summary = "Log in",
            description = "Exchanges email and password for a signed JWT (1-hour expiry). " +
                    "Include the returned token as 'Authorization: Bearer <token>' on " +
                    "protected requests."
    )
    @SecurityRequirements
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
