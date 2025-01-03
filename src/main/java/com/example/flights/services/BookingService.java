package com.example.flights.services;

import java.util.List;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.BookingDto;
import com.example.flights.dto.BookingReqDto;

public interface BookingService {

     ApiRes<BookingDto> bookFlight(BookingReqDto bookingReqDto);

     ApiRes<BookingDto> getBookingDetails(String bookingid);
     
      ApiRes<List<BookingDto>> getUserBookings(String userId);


}
