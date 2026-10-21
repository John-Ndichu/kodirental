package com.johnndichu.kodirental.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.johnndichu.kodirental.dtos.ContributorResponse;
import com.johnndichu.kodirental.dtos.RegisterContributorRequest;
import com.johnndichu.kodirental.entity.Contributor;
import com.johnndichu.kodirental.repository.ContributorRepository;
import com.johnndichu.kodirental.repository.RentSubmissionRepository;

@Service
public class ContributorService {
    private final ContributorRepository contributorRepository;
    private final PasswordEncoder passwordEncoder;

    public ContributorService(RentSubmissionRepository rentSubmissionRepository, ContributorRepository contributorRepository, PasswordEncoder passwordEncoder) {
        this.contributorRepository = contributorRepository;
        this.passwordEncoder = passwordEncoder;
    }


}
