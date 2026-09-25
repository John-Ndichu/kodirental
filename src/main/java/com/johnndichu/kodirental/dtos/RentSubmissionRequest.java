package com.johnndichu.kodirental.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

import com.johnndichu.kodirental.entity.HouseType;

public record RentSubmissionRequest(

        @NotNull(message = "areaId is required")
        UUID areaId,

        @NotNull(message = "houseType is required")
        HouseType houseType,

        @NotNull(message = "amount is required")
        @Positive(message = "amount must be greater than zero")
        @DecimalMax(value = "10000000", message = "amount seems unrealistically high")
        BigDecimal amount,

        boolean utilitiesIncluded
) {
}