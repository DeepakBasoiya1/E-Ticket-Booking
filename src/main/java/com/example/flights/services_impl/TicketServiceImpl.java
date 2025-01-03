package com.example.flights.services_impl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flights.dto.ApiRes;
import com.example.flights.entity.Booking;
import com.example.flights.entity.Flight;
import com.example.flights.entity.Layovers;
import com.example.flights.repository.BookingRepo;
import com.example.flights.repository.FlightRepo;
import com.example.flights.services.TicketService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    FlightRepo flightRepo;

    public ApiRes<byte[]> generateTicket(String bookingid) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Booking booking = bookingRepo.findById(bookingid).orElse(null);
            if (booking == null) {
                return new ApiRes<>(404, false, "Booking not found", null);
            }

            Flight flight = flightRepo.findById(booking.getFlightId()).orElse(null);
            if (flight == null) {
                return new ApiRes<>(404, false, "Flight not found", null);
            }

            List<Layovers> layovers = flight.getLayovers();

            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("......................................E-Ticket Booking System...................................................."));
            document.add(new Paragraph("Flight: " + booking.getFlightName()));
            document.add(new Paragraph("From: " + booking.getSource() + "                                                     " + "To: " + booking.getDestinationCity()));
            document.add(new Paragraph("Date of Journey: " + booking.getDoj()));
            document.add(new Paragraph("Departure Time: " + "12:30"));
            document.add(new Paragraph("Arrival Time: " + "14:30"));
            document.add(new Paragraph("User Name: " + booking.getUser().getName()));
            document.add(new Paragraph("Price: $" + booking.getPrice()));
            document.add(new Paragraph("checkInBaggage: " + flight.getCheckInBag() + "kg"));
            document.add(new Paragraph("CarryOnBaggage: " + flight.getCabinBag() + "kg"));

            for (Layovers layover : layovers) {
                document.add(new Paragraph("-------------------------------------------------"));
                document.add(new Paragraph("Layover Location: " + layover.getLayoverLocation()));
                document.add(new Paragraph("Layover Duration: " + layover.getLayoverDuration()));
                document.add(new Paragraph("Layover Start Time: " + layover.getLayoverStartTime()));
                document.add(new Paragraph("Layover End Time: " + layover.getLayoverEndTime()));
                document.add(new Paragraph("Layover Departure Airport: " + layover.getLayoverDepartureAirport()));
                document.add(new Paragraph("Layover Arival Airport: " + layover.getLayoverArivalAirport()));
                document.add(new Paragraph("Terminal Info: " + layover.getTerminalInfo()));
                document.add(new Paragraph("Flight ID: " + layover.getFlightId()));
                document.add(new Paragraph("Flight Name: " + layover.getFlightName()));
                document.add(new Paragraph("Layover ID: " + layover.getLayoverId()));
                document.add(new Paragraph("-------------------------------------------------"));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ApiRes<>(200, true, "Ticket genrate Sucessfully", outputStream.toByteArray());
    }
}
