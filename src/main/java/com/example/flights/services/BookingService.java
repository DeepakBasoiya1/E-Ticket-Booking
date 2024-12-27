package com.example.flights.services;

import java.util.List;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.BookingDto;

public interface BookingService {

     ApiRes<BookingDto> bookFlight(String userId, String flightId, String passangerName, String passangerEmail);

     ApiRes<BookingDto> getBookingDetails(String bookingid);

      ApiRes<List<BookingDto>> getUserBookings(String userId);

}
