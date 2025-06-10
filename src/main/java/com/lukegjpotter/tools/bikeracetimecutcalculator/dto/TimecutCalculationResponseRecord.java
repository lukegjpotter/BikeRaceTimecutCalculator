package com.lukegjpotter.tools.bikeracetimecutcalculator.dto;

public record TimecutCalculationResponseRecord(
        String maximumGapToWinner,
        String maximumRaceDuration,
        String errorMessage) {
}
