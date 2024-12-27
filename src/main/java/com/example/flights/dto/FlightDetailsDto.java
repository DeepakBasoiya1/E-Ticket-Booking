package com.example.flights.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightDetailsDto {
    private String flightId;
    private String flightName;
    private String source;
    private String destination;
    private String price;
    private String originAirpot;
    private String destinationAirport;
    private String originAirportTerminal;
    private String destinationAirportTerminal;
    private int checkInBag;
    private int cabinBag;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalDate departureDate;
    private LocalDate arivalDate;
    private String flightClass;
    private List<LayoverDto> layovers;
    
    //List<LayoverDto> layovers;

}
