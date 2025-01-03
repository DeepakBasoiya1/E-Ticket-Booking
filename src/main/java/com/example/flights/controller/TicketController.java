package com.example.flights.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flights.dto.ApiRes;
import com.example.flights.services.TicketService;
import com.example.flights.services_impl.TicketServiceImpl;

@RestController
@RequestMapping("/api/flights/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadTicket(
            @RequestParam("bookingid") String bookingid
    ) {

        // Generate the PDF bytes
        ApiRes<byte[]> apiResponse = ticketService.generateTicket(bookingid);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (!apiResponse.isStatus()) {
            return ResponseEntity.status(status).body(apiResponse.getData());
        }

        // Set headers to indicate a downloadable PDF file
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        return ResponseEntity.status(status).headers(headers).body(apiResponse.getData());
    }
}
