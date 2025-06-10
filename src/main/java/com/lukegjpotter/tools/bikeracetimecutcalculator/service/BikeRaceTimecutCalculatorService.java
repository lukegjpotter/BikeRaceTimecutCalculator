package com.lukegjpotter.tools.bikeracetimecutcalculator.service;

import com.lukegjpotter.tools.bikeracetimecutcalculator.dto.RaceFinishRequestRecord;
import com.lukegjpotter.tools.bikeracetimecutcalculator.dto.TimecutCalculationResponseRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalTime;

@Service
public class BikeRaceTimecutCalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(BikeRaceTimecutCalculatorService.class);

    public BikeRaceTimecutCalculatorService() {
    }

    private static int localTimeAsSeconds(LocalTime localTime) {
        return (localTime.getHour() * 3600)
                + (localTime.getMinute() * 60)
                + localTime.getSecond();
    }

    private static String durationToFormattedString(Duration duration) {
        return String.format("%02d:%02d:%02d",
                duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart());
    }

    public TimecutCalculationResponseRecord calculate(final RaceFinishRequestRecord raceFinishRequestRecord) throws IllegalArgumentException {

        if (raceFinishRequestRecord.percentageTimeCut() <= 0)
            throw new IllegalArgumentException(
                    MessageFormat.format("The value for the percentageTimeCut must be positive, you supplied {0}.", raceFinishRequestRecord.percentageTimeCut()));

        // Convert String to Duration.
        LocalTime raceDurationLocalTime = LocalTime.parse(raceFinishRequestRecord.raceDuration());
        Duration raceDuration = Duration.ofSeconds(localTimeAsSeconds(raceDurationLocalTime));

        // Factor in the % timecut, to get the Maximum time.
        double percentageIncreaseAsDecimal = ((double) raceFinishRequestRecord.percentageTimeCut() / 100) + 1;
        Duration maxRaceDuration = Duration.ofSeconds((long) (raceDuration.toSeconds() * percentageIncreaseAsDecimal));
        String maximumRaceDuration = durationToFormattedString(maxRaceDuration);

        // Calculate Time Gap.
        Duration maxTimeGapToWinner = Duration.between(raceDurationLocalTime, LocalTime.parse(maximumRaceDuration));
        String maximumGapToWinner = durationToFormattedString(maxTimeGapToWinner);

        return new TimecutCalculationResponseRecord(
                maximumGapToWinner,
                maximumRaceDuration,
                "");
    }
}
