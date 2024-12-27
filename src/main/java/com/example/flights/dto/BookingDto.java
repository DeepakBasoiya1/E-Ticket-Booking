package com.example.flights.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDto {

    private String message;
    private String bookingId;
    private String username;
    private String userEmail;
    private LocalDate bookingDate;
    private String price;
    private LocalDate doj;
    private String flightName;
    private String source;
    private String destinationCity;
    private String flightId;

}
