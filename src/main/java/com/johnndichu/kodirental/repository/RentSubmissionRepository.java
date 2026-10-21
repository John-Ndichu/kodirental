package com.johnndichu.kodirental.repository;

import com.johnndichu.kodirental.entity.HouseType;
import com.johnndichu.kodirental.entity.RentSubmission;
import com.johnndichu.kodirental.entity.SubmissionStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentSubmissionRepository extends JpaRepository<RentSubmission, UUID> {
    List<RentSubmission> findByAreaIdAndHouseTypeAndStatus(
            UUID areaId,
            HouseType houseType,
            SubmissionStatus status
    );

    List<RentSubmission> findByStatus(SubmissionStatus status);

    List<RentSubmission> findByContributorIdAndAreaIdAndHouseTypeAndUtilitiesIncludedAndStatus(
            UUID contributorId,
            UUID areaId,
            HouseType houseType,
            boolean utilitiesIncluded,
            SubmissionStatus status
    );

    List<RentSubmission> findByContributorIdAndStatus(UUID contributorId, SubmissionStatus status);
}
