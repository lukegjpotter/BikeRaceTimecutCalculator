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
                        "maximumRaceDuration", is("04:30:00"),
                        "errorMessage", emptyString());
    }

    @Test
    void testCalculate_knownRaceData_Dauphine() {
        given()
                .contentType(ContentType.JSON)
                .body(new RaceFinishRequestRecord("04:34:10", 10))
                .when()
                .post("/calculate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "maximumGapToWinner", is("00:27:25"),
                        "maximumRaceDuration", is("05:01:35"),
                        "errorMessage", emptyString());
    }

    @Test
    void testCalculate_knownRaceData_Cameroun() {
        given()
                .contentType(ContentType.JSON)
                .body(new RaceFinishRequestRecord("01:58:58", 15))
                .when()
                .post("/calculate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "maximumGapToWinner", is("00:17:50"),
                        "maximumRaceDuration", is("02:16:48"),
                        "errorMessage", emptyString());
    }

    @Test
    void testCalculate_knownRaceData_AntwerpPortEpic() {
        given()
                .contentType(ContentType.JSON)
                .body(new RaceFinishRequestRecord("04:12:09", 8))
                .when()
                .post("/calculate")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "maximumGapToWinner", is("00:20:10"),
                        "maximumRaceDuration", is("04:32:19"),
                        "errorMessage", emptyString());
    }

    @Test
    void testCalculate_parsingExceptions_AssertionError() {
        given()
                .contentType(ContentType.JSON)
                .body(new RaceFinishRequestRecord("44:12:09", 8))
                .when()
                .post("/calculate")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(
                        "maximumGapToWinner", nullValue(),
                        "maximumRaceDuration", nullValue(),
                        "errorMessage", is("Text '44:12:09' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 44"));
    }

    @Test
    void testCalculate_parsingExceptions_Text() {
        given()
                .contentType(ContentType.JSON)
                .body(new RaceFinishRequestRecord("Liverpool", 8))
                .when()
                .post("/calculate")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(
                        "maximumGapToWinner", nullValue(),
                        "maximumRaceDuration", nullValue(),
                        "errorMessage", is("Text 'Liverpool' could not be parsed at index 0"));
    }

    // ToDo tests: nulls, negative values
}