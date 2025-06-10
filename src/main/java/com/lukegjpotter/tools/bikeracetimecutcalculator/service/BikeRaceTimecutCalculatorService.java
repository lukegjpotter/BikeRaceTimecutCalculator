package com.lukegjpotter.tools.bikeracetimecutcalculator.service;

import com.lukegjpotter.tools.bikeracetimecutcalculator.dto.RaceFinishRequestRecord;
import com.lukegjpotter.tools.bikeracetimecutcalculator.dto.TimecutCalculationResponseRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BikeRaceTimecutCalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(BikeRaceTimecutCalculatorService.class);

    public BikeRaceTimecutCalculatorService() {
    }

    public TimecutCalculationResponseRecord calculate(RaceFinishRequestRecord raceFinishRequestRecord) {
        return new TimecutCalculationResponseRecord(
                "00:45:00",
                "04:30:00",
                "");
    }
}
