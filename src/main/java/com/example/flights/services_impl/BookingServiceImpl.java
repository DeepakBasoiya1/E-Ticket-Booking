package com.example.flights.services_impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.BookingDto;
import com.example.flights.dto.BookingReqDto;
import com.example.flights.entity.Booking;
import com.example.flights.entity.Flight;
import com.example.flights.entity.User;
import com.example.flights.repository.BookingRepo;
import com.example.flights.repository.FlightRepo;
import com.example.flights.repository.UserRepo;
import com.example.flights.services.BookingService;
import com.example.flights.services.EmailService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional
public class BookingServiceImpl implements BookingService  {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EmailService emailService;

    @Autowired
    FlightRepo flightRepo;




    public ApiRes<BookingDto> bookFlight(@RequestBody BookingReqDto bookingReqDto) {
        String userId = bookingReqDto.getUserId();
        String flightId = bookingReqDto.getFlightId();
        String passangerName = bookingReqDto.getPassangerName();
        String passangerEmail = bookingReqDto.getPassangerEmail();
        try { 
            User user = userRepo.findById(userId).orElse(null);
            if (user == null) {
                return new ApiRes<>(404,false,"User not found",null);
            }

            Flight flight = flightRepo.findByIdWithLock(flightId);
            if (flight == null) {
                return new ApiRes<>(404,false,"Flight not found",null);
            }

            if (flight.getSeats() <= 0) {
                return new ApiRes<>(404,false,"no seat left in this flight",null);
            }

            flight.setSeats(flight.getSeats() - 1);
            flightRepo.save(flight);

            Booking booking = new Booking();
            booking.setPasangerName(passangerName);
            booking.setPasangerEmail(passangerEmail);
            booking.setBookingDate(LocalDate.now());
            booking.setFlightId(flightId);
            booking.setPrice(flight.getPrice());
            booking.setFlightName(flight.getFlightName());
            booking.setSource(flight.getSource());
            booking.setDestinationCity(flight.getDestination());
            booking.setDoj(flight.getDepartureDate());
            booking.setUserId(user.getUserId());
            booking.setUser(user);
            booking.setBookingId(UUID.randomUUID().toString());
            booking.setMessage("Your booking is confirmed for flightRepo "+flight.getFlightName()+" from "+flight.getSource()+" to "+flight.getDestination()+" on "+booking.getBookingDate()+" with booking id "+booking.getBookingId());
            bookingRepo.save(booking);

            emailService.sendEmail(
                passangerEmail,
                "E-Ticket-Booking",
                String.format("Your booking is confirmed for flightRepo %s from %s to %s on %s with booking id %s",
                    flight.getFlightName(),
                    flight.getSource(),
                    flight.getDestination(),
                    booking.getBookingDate(),
                    booking.getBookingId())
            );
            
            user.getBookings().add(booking);
            userRepo.save(user);

            log.info("Flight booked successfully - Flight ID: {}, Booking ID: {}", flightId, booking.getBookingId());
            BookingDto bookingDto = modelMapper.map(booking,BookingDto.class);
            bookingDto.setMessage(booking.getBookingId() +" is your bookingid ");
            return new ApiRes<>(200,true,"Flight Booked Succesfull",bookingDto);

        } catch (Exception e) {
            log.error("Error booking flightRepo: {}", e.getMessage(), e);
            return new ApiRes<>(500,false,"An error occurred while booking the flightRepo: " + e.getMessage(),null);
        }
    }

    public ApiRes<BookingDto> getBookingDetails(String bookingid) {

        if(bookingid==null){
            return new ApiRes<>(400,false,"Booking ID cannot be null",null);
        }
        if(bookingid.isEmpty()){
            return new ApiRes<>(400,false,"Booking ID cannot be empty",null);
        }

        try {
            Booking booking = bookingRepo.findById(bookingid).orElse(null);
            if (booking == null) {
                return new ApiRes<>(404,false,"Booking Not Found",null);
            }
            BookingDto bookingDto = modelMapper.map(booking,BookingDto.class);
            return new ApiRes<>(200,true,"Booking fetch Sucesfull",bookingDto);
        } catch (Exception e) {
            return new ApiRes<>(500,false,"An error occurred while fetching booking details: " + e.getMessage(),null);
        }
    }
    
    @Override
    public ApiRes<List<BookingDto>> getUserBookings(String userId) {
        if(userId==null){
            return new ApiRes<>(400,false,"User ID cannot be null",null);
        }
        if(userId.isEmpty()){
            return new ApiRes<>(400,false,"User ID cannot be empty",null);
        }
        try {
            List<BookingDto> bookingDtos = new ArrayList<BookingDto>();
            User user = userRepo.findById(userId).orElse(null);
            if (user == null) {
                return new ApiRes<>(404,false,"User Not Found",null);
            }
                List<Booking> bookings = user.getBookings();
                for(Booking booking : bookings) {
                    BookingDto bookingDto = modelMapper.map(booking,BookingDto.class);
                    bookingDto.setMessage(bookingDto.getBookingId() +" is you booking id");
                    bookingDtos.add(bookingDto);
                }
            
                return new ApiRes<>(200,true,"booking fetch Sucesfull",bookingDtos);
        } catch (Exception e) {
            return new ApiRes<>(500,false,"An error occurred while fetching userRepo bookings: " + e.getMessage(),null);
        }
    }

}
