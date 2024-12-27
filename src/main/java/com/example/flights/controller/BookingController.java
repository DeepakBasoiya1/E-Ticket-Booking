package com.example.flights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.BookingDto;
import com.example.flights.services.BookingService;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/api/flights")
public class BookingController {

    @Autowired
    BookingService bookingService;


    @GetMapping("/getbooking")
    public ResponseEntity<ApiRes<BookingDto>> getBookingDetails(
            @RequestParam(value = "bookingid", required = false)
            @NotNull(message = "Booking ID cannot be null")
            @NotEmpty(message = "Booking ID cannot be empty")
            String bookingid) {

        ApiRes<BookingDto> apiResponse = bookingService.getBookingDetails(bookingid);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(apiResponse);
    }

    @GetMapping("/getbookings")
    public ResponseEntity<ApiRes<List<BookingDto>>> getUserBookings
    (@RequestParam(value = "userid", required = false)
    @NotNull(message = "User ID cannot be null")
    @NotEmpty(message = "User ID cannot be empty")
     String userid) {
        ApiRes<List<BookingDto>> apiResponse = bookingService.getUserBookings(userid);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(apiResponse);
    }

    @PostMapping("/bookflight")
    public ResponseEntity<ApiRes<BookingDto>> bookFlight(
            @RequestParam(value = "userid",required = false) 
            @NotNull(message = "User ID cannot be null")
            @NotEmpty(message = "User ID cannot be empty")
            String userid,

            @RequestParam(value = "flightid", required = false)
            @NotNull(message = "Flight ID cannot be null")
            @NotEmpty(message = "Flight ID cannot be empty")
            String flightid,

            @RequestParam(value = "passangerName",required = false) 
            @NotNull(message = "Passanger Name cannot be null")
            @NotEmpty(message = "Passanger Name cannot be empty")
            String passangerName,

            @RequestParam(value = "passangerEmail", required = false) 
            @NotNull(message = "Passanger Email cannot be null")
            @NotEmpty(message = "Passanger Email cannot be empty")
            @Email(message = "Invalid Email")
            String passangerEmail) {

        ApiRes<BookingDto> apiResponse = bookingService.bookFlight(userid, flightid, passangerName, passangerEmail);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(apiResponse);
    }

}
