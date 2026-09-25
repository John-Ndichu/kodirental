package com.johnndichu.kodirental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnndichu.kodirental.entity.Region;

import java.util.UUID;

public interface RegionRepository extends JpaRepository<Region, UUID> {
}
