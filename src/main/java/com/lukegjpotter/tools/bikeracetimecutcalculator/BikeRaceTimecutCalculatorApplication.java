package com.lukegjpotter.tools.bikeracetimecutcalculator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Bike Race Timecut Calculator", version = "0.0.1",
        description = "A RESTful Service take takes some parameters and then generates the Time Cut for a Bike Race."))
public class BikeRaceTimecutCalculatorApplication {

    private static final Logger logger = LoggerFactory.getLogger(BikeRaceTimecutCalculatorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BikeRaceTimecutCalculatorApplication.class, args);

        logger.info("Application Started");
    }

}
