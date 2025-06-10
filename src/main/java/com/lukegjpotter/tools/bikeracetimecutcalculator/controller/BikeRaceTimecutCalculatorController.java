package com.lukegjpotter.tools.bikeracetimecutcalculator.controller;

import com.lukegjpotter.tools.bikeracetimecutcalculator.dto.RaceFinishRequestRecord;
import com.lukegjpotter.tools.bikeracetimecutcalculator.dto.TimecutCalculationResponseRecord;
import com.lukegjpotter.tools.bikeracetimecutcalculator.service.BikeRaceTimecutCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BikeRaceTimecutCalculatorController {

    private static final Logger logger = LoggerFactory.getLogger(BikeRaceTimecutCalculatorController.class);
    private final BikeRaceTimecutCalculatorService bikeRaceTimecutCalculatorService;

    public BikeRaceTimecutCalculatorController(BikeRaceTimecutCalculatorService bikeRaceTimecutCalculatorService) {
        this.bikeRaceTimecutCalculatorService = bikeRaceTimecutCalculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<TimecutCalculationResponseRecord> calculate(@RequestBody RaceFinishRequestRecord raceFinishRequestRecord) {
        logger.trace("Endpoint Calculate called with {}", raceFinishRequestRecord);

        try {
            TimecutCalculationResponseRecord timecutCalculationResponseRecord = bikeRaceTimecutCalculatorService.calculate(raceFinishRequestRecord);

            if (timecutCalculationResponseRecord.errorMessage().isEmpty())
                return ResponseEntity.ok(timecutCalculationResponseRecord);
            else return ResponseEntity.badRequest().body(timecutCalculationResponseRecord);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new TimecutCalculationResponseRecord(
                    null,
                    null,
                    exception.getMessage()));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<TimecutCalculationResponseRecord> test() {
        logger.trace("Endpoint Test called");

        return ResponseEntity.ok(new TimecutCalculationResponseRecord(
                "00:45:00",
                "04:30:00",
                ""));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        logger.trace("Endpoint Health called");
        return ResponseEntity.ok("OK");
    }
}
