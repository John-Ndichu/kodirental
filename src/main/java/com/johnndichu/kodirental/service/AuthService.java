package com.johnndichu.kodirental.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.johnndichu.kodirental.dtos.ContributorResponse;
import com.johnndichu.kodirental.dtos.LoginRequest;
import com.johnndichu.kodirental.dtos.LoginResponse;
import com.johnndichu.kodirental.dtos.RegisterContributorRequest;
import com.johnndichu.kodirental.entity.Contributor;
import com.johnndichu.kodirental.repository.ContributorRepository;
import com.johnndichu.kodirental.security.JwtService;


@Service
public class AuthService {
    private final ContributorRepository contributorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(ContributorRepository contributorRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.contributorRepository = contributorRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ContributorResponse register(RegisterContributorRequest request) {

        if (contributorRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        String hashedPassword = passwordEncoder.encode(request.password());

        Contributor contributor = Contributor.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(hashedPassword)
                .enabled(true)
                .build();

        Contributor savedContributor = contributorRepository.save(contributor);

        return new ContributorResponse(
                savedContributor.getId(),
                savedContributor.getFirstName(),
                savedContributor.getLastName(),
                savedContributor.getEmail(),
                savedContributor.isEnabled()
        );
    }

    public LoginResponse login(LoginRequest request) {

        Contributor contributor = contributorRepository
                .findByEmail(request.email())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email or password")
                );

        if (!passwordEncoder.matches(
                request.password(),
                contributor.getPassword()
        )) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(contributor.getEmail());

        return new LoginResponse(token);
    }
}
