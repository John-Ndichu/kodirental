package com.johnndichu.kodirental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnndichu.kodirental.entity.Area;
import com.johnndichu.kodirental.entity.Region;

import java.util.List;
import java.util.UUID;

public interface AreaRepository extends JpaRepository<Area, UUID> {
    List<Area> findByRegionId(UUID regionId);

    List<Area> findByNameContainingIgnoreCase(String name);

    List<Area> findByRegionIdAndNameContainingIgnoreCase(UUID regionId, String name);

    List<Area> findByRegionNameIgnoreCase(String regionName);

    List<Area> findByRegionNameIgnoreCaseAndNameContainingIgnoreCase(String regionName, String name);
}
