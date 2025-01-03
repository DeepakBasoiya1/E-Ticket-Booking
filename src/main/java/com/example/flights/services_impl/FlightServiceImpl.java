
package com.example.flights.services_impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.flights.dto.ApiRes;
import com.example.flights.dto.FlightDetailsDto;
import com.example.flights.dto.FlightDto;
import com.example.flights.dto.SearchDto;
import com.example.flights.entity.Flight;
import com.example.flights.entity.Layovers;
import com.example.flights.repository.BookingRepo;
import com.example.flights.repository.FlightRepo;
import com.example.flights.repository.LayoverRepo;
import com.example.flights.repository.UserRepo;
import com.example.flights.services.EmailService;
import com.example.flights.services.FlightService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepo flightRepo;

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    LayoverRepo layoverRepo;

    @Autowired
    EmailService emailService;


    @Autowired
    private ModelMapper modelMapper;


    public List<Flight> getFilteredFlights(String source, String destination, LocalDate date, String flightClass) {
        LocalDateTime startDate = date.atTime(LocalTime.now()); // 2024-12-21T00:00
        LocalDateTime endDate = date.atTime(23, 59, 59); // 2024-12-21T23:59:59
        log.info(startDate.toString());
        log.info(endDate.toString());

        return flightRepo.findFlightsByFilter(source, destination, startDate, endDate, flightClass);
    }


    public ApiRes<List<FlightDto>> getFlights(SearchDto searchDto) {
        try {
            // List<Flight> flights = flightRepo.findAll();


             List<FlightDto> flightDtoList = new ArrayList<>();
             String source = searchDto.getSource();
             String destination = searchDto.getDestination();
             String flightClass = searchDto.getFlightClass();
             LocalDateTime dateTime = searchDto.getDepartureDate();
             LocalDate date = dateTime.toLocalDate();
             log.info(dateTime.toString());
             log.info(date.toString());
             List<Flight> flights = getFilteredFlights(source, destination, date,flightClass);
             if(flights.isEmpty()) {    
               return new ApiRes<>(200, true, "there is no flight available ", null);
             }
                 for (Flight flight : flights) {
                      FlightDto flightDto = modelMapper.map(flight, FlightDto.class); 
                      flightDtoList.add(flightDto);
             }
            return new ApiRes<>(200, true, "flight data sucesfully fetched ", flightDtoList);
         } catch (Exception e) {
             return new ApiRes<>(500, false, "An error occurred while fetching flights: " + e.getMessage(), null);
    }
}

public ApiRes<FlightDetailsDto> getFlightDetails(String flightId) {
    try {
        Flight flight = flightRepo.findById(flightId).orElse(null);
        if (flight == null) {
            return new ApiRes<>(404, false, "Flight not found", null);
        }
        FlightDetailsDto flightDetailsDto = modelMapper.map(flight, FlightDetailsDto.class);
        return new ApiRes<>(200, true, "Flight Details Fetched Successfully", flightDetailsDto);
    } catch (Exception e) {
        return new ApiRes<>(500, false, "An error occurred while fetching the flight: " + e.getMessage(), null);
    }
}
@Transactional
public ApiRes<Flight> addFlight(Flight flight) {
    try {
        // Set the Flight entity in each Layovers entity
        if (flight.getLayovers() != null) {
            for (Layovers layover : flight.getLayovers()) {
                layover.setFlight(flight); // Associate the flight with the layover
            }
        }
        // Save the Flight entity
        flightRepo.save(flight);
        return new ApiRes<>(200, true, "Flight added successfully", flight);
    } catch (Exception e) {
        return new ApiRes<>(500, false, "An error occurred while adding the flight: " + e.getMessage(), null);
    }
}
}
