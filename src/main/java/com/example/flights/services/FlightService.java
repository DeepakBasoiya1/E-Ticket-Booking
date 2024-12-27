package com.example.flights.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.FlightDetailsDto;
import com.example.flights.dto.FlightDto;
import com.example.flights.entity.Flight;

public interface FlightService {

    public List<Flight> getFilteredFlights(String source, String destination, LocalDate date, String flightClass);

    public ApiRes<List<FlightDto>> getFlights(String source, String destination, LocalDateTime dateTime, String flightClass);

    public ApiRes<FlightDetailsDto> getFlightDetails(String flightId);

    public ApiRes<Flight> addFlight(Flight flight);  


} 
