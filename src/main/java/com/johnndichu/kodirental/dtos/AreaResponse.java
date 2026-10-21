package com.johnndichu.kodirental.dtos;

import java.util.UUID;

public record AreaResponse(
        UUID id,
        String name,
        UUID regionId,
        String regionName
) {
}