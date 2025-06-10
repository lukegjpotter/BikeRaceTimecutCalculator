package com.lukegjpotter.tools.bikeracetimecutcalculator.dto;

public record TimecutCalculationResponseRecord(
        String maximumGapToWinner,
        String maximumRaceTime,
        String errorMessage) {
}
