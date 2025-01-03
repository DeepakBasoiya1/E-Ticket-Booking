package com.example.flights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.BookingDto;
import com.example.flights.dto.BookingReqDto;
import com.example.flights.services.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/flights")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/getbooking/{bookingId}")
    public ResponseEntity<ApiRes<BookingDto>> getBookingDetails(
            @PathVariable(value = "bookingId", required = true) String bookingId) {

        ApiRes<BookingDto> apiResponse = bookingService.getBookingDetails(bookingId);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(apiResponse);
    }

    @PostMapping("/bookflight")
    public ResponseEntity<ApiRes<BookingDto>> bookFlight(@Valid @RequestBody BookingReqDto bookingReqDto) {

        ApiRes<BookingDto> apiResponse = bookingService.bookFlight(bookingReqDto);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(apiResponse);
    }
}
