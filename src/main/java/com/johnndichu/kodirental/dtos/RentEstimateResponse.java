package com.johnndichu.kodirental.dtos;

import java.util.UUID;

import com.johnndichu.kodirental.entity.HouseType;

public record RentEstimateResponse(
        UUID areaId,
        String areaName,
        HouseType houseType,
        RentEstimateSegment utilitiesIncluded,
        RentEstimateSegment utilitiesExcluded
) {
}
