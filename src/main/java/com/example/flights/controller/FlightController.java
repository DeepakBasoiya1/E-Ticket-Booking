package com.example.flights.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.FlightDetailsDto;
import com.example.flights.dto.FlightDto;
import com.example.flights.entity.Flight;
import com.example.flights.services.FlightService;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Validated
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    FlightService flightService;
   

    @GetMapping("/getflights")
    public ResponseEntity<ApiRes<List<FlightDto>>> getFlights(
            @RequestParam(value = "source") 
            @NotNull(message = "Source cannot be null")
            @NotEmpty(message = "Source cannot be empty")
            String source,

            @RequestParam(value = "destination") 
            @NotNull(message = "Destination cannot be null")
            @NotEmpty(message = "Destination cannot be empty")
            String destination,
            

            @RequestParam(value = "date")
            @NotNull(message = "Date cannot be null")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
            LocalDateTime date, 

            @RequestParam(value = "flightClass", defaultValue = "economy") String flightClass) {

        ApiRes<List<FlightDto>> apiResponse = flightService.getFlights(source, destination, date, flightClass);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(apiResponse);

    }

    @GetMapping("/getFlight/{flightid}")
    public ResponseEntity<ApiRes<FlightDetailsDto>> getflightDeatails(@PathVariable(value = "flightid", required = true) String flightid) {
        ApiRes<FlightDetailsDto> apiResponse = flightService.getFlightDetails(flightid);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(apiResponse);
    }

    @PostMapping("/addflight")
    public ResponseEntity<ApiRes<Flight>> addFlight(@RequestBody Flight flight) {
        ApiRes<Flight> apiResponse = flightService.addFlight(flight);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(apiResponse);

    }
}
