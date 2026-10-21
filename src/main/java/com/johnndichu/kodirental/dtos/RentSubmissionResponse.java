package com.johnndichu.kodirental.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.johnndichu.kodirental.entity.HouseType;
import com.johnndichu.kodirental.entity.SubmissionStatus;

public record RentSubmissionResponse(
        UUID id,
        UUID contributorId,
        UUID areaId,
        HouseType houseType,
        BigDecimal amount,
        SubmissionStatus status,
        boolean utilitiesIncluded
) {
}
