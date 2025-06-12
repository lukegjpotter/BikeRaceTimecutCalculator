# Bike Race Timecut Calculator

A Spring Boot REST API for a Bike Race Timecut Calculator.

[![Deploy to Render](https://render.com/images/deploy-to-render-button.svg)](https://render.com/deploy?repo=https://github.com/lukegjpotter/BikeRaceTimecutCalculator)

## The Goal

To create a simple tool to calculate the maximum time gap, aka the Time Cut for Cycling Races.

## Deployed on Render

This service is deployed live on Render, to use it check out this Postman
Collection: [VAM to WKG Converter](https://www.postman.com/bold-moon-552911/bikeracetimecutcalculator/collection/4v3fjg6/bikeracetimecutcalculator?action=share&creator=3947605&active-environment=3947605-ab751e14-7e85-4c82-b013-6e636cba23a8).  
It has a prefilled JSON Body ready to receive your inputs.

Alternatively, you can use `curl`:

    curl -X POST https://bikeracetimecutcalculator.onrender.com/calculate \
         -H 'Content-type:application/json' \
         -d '{
                "raceDuration": "03:45:00",
                "percentageTimeCut": 12
            }' | json

Ensure that you install the `json` tool to format/pretty print the Response.

    sudo npm i -g json

You should get a response that looks like this:

    {
      "maximumGapToWinner": "00:27:00",
      "maximumRaceDuration": "04:12:00",
      "errorMessage": ""
    }

## Swagger / Spring-Doc / OpenDoc

To view the Swagger UI, click this link to view it on
Render: [Swagger UI](https://bikeracetimecutcalculator.onrender.com/swagger-ui/index.html).

## Build, Run and Test

This is a Spring Boot 3 project, so it requires Java 17.  
It also runs on Port 8080, so it ensure that port is not being used when running it.

### Command Line (CLI)

Build and Run the application locally by using the Gradle Wrapper.

    ./gradlew clean build bootRun

For subsequent runs, where you haven't made Source Code changes, you don't need the `clean` and `build` tasks,  
so `./gradlew bootRun` will do the job.

### Docker

Build the Docker Image with the following Command:

    docker build --pull -t bike-race-timecut-calculator:latest .

This command presumes that you are in the directory with this application's Dockerfile.  
You can see the built Image with `docker image ls`.  
Later you can remove the Image with `docker image rm <image id sha>`.

Run the Image as a Docker Container with the following Command:

    docker run --name bike-race-timecut-calculator \
       -p 8080:8080 \
       -d --rm bike-race-timecut-calculator:latest

You can see the Container running with `docker ps` or `docker container ls`,  
Although, the `run` command has the `--rm` flag set, so the container will remove itself after you stop it, so it will
not be shown in `conatiner ls` after you stop the Container.  
Stop it with `docker stop bike-race-timecut-calculator`.

### Test with Postman and curl

For both ways to run this application, CLI and Docker, you can test the application with `curl` or Postman with
`http://localhost:8080/calculate` as the URL.  
Note that it is `http`, and not `https`.

The following `curl` command will operate both the Docker and GradleW run application:

    curl -X POST http://localhost:8080/calculate \
         -H 'Content-type:application/json' \
         -d '{
                "raceDuration": "03:45:00",
                "percentageTimeCut": 12
            }' | json
