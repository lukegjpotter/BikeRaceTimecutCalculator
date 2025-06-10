# Bike Race Timecut Calculator

A Spring Boot REST API for a Bike Race Timecut Calculator.

[![Deploy to Render](https://render.com/images/deploy-to-render-button.svg)](https://render.com/deploy?repo=https://github.com/lukegjpotter/BikeRaceTimecutCalculator)

## The Goal

To create a simple tool to calculate the maximum time gap, aka the Time Cut for Cycling Races.

## Deployed on Render

This service is deployed live on Render, to use it check out this Postman Collection: [VAM to WKG Converter(/#.  
It has a prefilled JSON Body ready to receive your inputs.

Alternatively, you can use `curl`:

    curl -X POST https://bikeracetimecutcalculator.onrender.com/calculate \
         -H 'Content-type:application/json' \
         -d '{"raceDuration": 03:45:00, "percentageTimeCut": 12}' | json

Ensure that you install the `json` tool to format/pretty print the Response.

    sudo npm i -g json

You should get a response that looks like this:

    {
      "maximumGapToWinner": 00:45:00,
      "maximumRaceTime": 4:30:00,
      "errorMessage": ""
    }

## Swagger / Spring-Doc / OpenDoc

To view the Swagger UI, click this link to view it on
Render: [Swagger UI](https://bikeracetimecutcalculator.onrender.com/swagger-ui/index.html).

## Build, Run and Test

To Do
