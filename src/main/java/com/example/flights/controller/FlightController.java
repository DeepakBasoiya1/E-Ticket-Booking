package com.example.flights.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.FlightDetailsDto;
import com.example.flights.dto.FlightDto;
import com.example.flights.dto.SearchDto;
import com.example.flights.entity.Flight;
import com.example.flights.repository.FlightRepo;
import com.example.flights.services.FlightService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Validated
@RequestMapping("/api/flights")
@Slf4j
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    private FlightRepo flightRepo;

    @GetMapping("/getflights")
    // @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ApiRes<List<FlightDto>>> getFlights(@Valid @RequestBody SearchDto searchDto) {
        // log.info("Details: {}" +
        // sc.getContext().getAuthentication().getDetails().toString());
        ApiRes<List<FlightDto>> apiResponse = flightService.getFlights(searchDto);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(apiResponse);
    }

    // @PreAuthorize("hasAnyRole('user', 'admin')")
    @GetMapping("/getFlight/{flightid}")
    public ResponseEntity<ApiRes<FlightDetailsDto>> getflightDeatails(
            @PathVariable(value = "flightid", required = true) String flightid) {
        ApiRes<FlightDetailsDto> apiResponse = flightService.getFlightDetails(flightid);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(apiResponse);
    }

    // @PreAuthorize("hasRole('admin')")
    @PostMapping("/addflight")
    public ResponseEntity<ApiRes<Flight>> addFlight(@RequestBody Flight flight) {
        ApiRes<Flight> apiResponse = flightService.addFlight(flight);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(apiResponse);

    }

    @GetMapping("/")
    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }
}
