package com.johnndichu.kodirental.dtos;

import java.util.UUID;

public record ContributorResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        boolean enabled
) {
}
