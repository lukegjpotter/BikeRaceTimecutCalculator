package com.lukegjpotter.tools.bikeracetimecutcalculator.controller;

import com.lukegjpotter.tools.bikeracetimecutcalculator.dto.RaceFinishRequestRecord;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BikeRaceTimecutCalculatorControllerTest {

    @BeforeAll
    static void beforeAll() {
        baseURI = "http://localhost:8080";
    }

    @Test
    @Order(1)
    public void healthCheck() {
        when()
                .get("/health")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo("OK"));
    }

    @Test
    @Order(2)
    void test_TestMethod() {
        when()
                .get("/test")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "maximumGapToWinner", is("00:45:00"),
                        "maximumRaceTime", is("04:30:00"),
                        "errorMessage", emptyString());
    }

    @Test
    void testCalculate_goldenPath1() {
        given()
                .contentType(ContentType.JSON)
                .body(new RaceFinishRequestRecord("03:45:00", 12.0))
                .when()
                .post("/calculate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "maximumGapToWinner", is("00:45:00"),
                        "maximumRaceTime", is("04:30:00"),
                        "errorMessage", emptyString());
    }
}