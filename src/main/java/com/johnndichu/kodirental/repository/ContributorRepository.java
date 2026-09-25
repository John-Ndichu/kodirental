package com.johnndichu.kodirental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnndichu.kodirental.entity.Contributor;

import java.util.Optional;
import java.util.UUID;

public interface ContributorRepository extends JpaRepository<Contributor, UUID> {

    boolean existsByEmail(String email);

    Optional<Contributor> findByEmail(String email);
}

