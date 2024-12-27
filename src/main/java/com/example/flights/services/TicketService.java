package com.example.flights.services;

import com.example.flights.dto.ApiRes;

public interface TicketService {

     public ApiRes<byte[]> generateTicket(String bookingId);
    
} 
