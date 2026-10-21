package com.johnndichu.kodirental.dtos;

import java.math.BigDecimal;

public record RentEstimateSegment(
        BigDecimal medianAmount,
        int sampleSize,
        double confidenceScore,
        String confidenceLabel
) {
}
