package com.example.flights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.BookingDto;
import com.example.flights.entity.User;
import com.example.flights.repository.UserCredRepo;
import com.example.flights.services.BookingService;
import com.example.flights.services.UserService;
//import com.example.flights.services_impl.UserCredServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // @Autowired
    // UserCredServiceImp userCredServiceImpl;

    @Autowired
    UserCredRepo userCredRepo;

    @Autowired 
    BookingService bookingService;

    @Autowired
    UserService userService;

    @PostMapping("/register") 
    public ResponseEntity<ApiRes<String>> registerUser(@Valid @RequestBody User user) {
        ApiRes<String> apiResponse = userService.registerUser(user);
        HttpStatus httpStatus = HttpStatus.resolve(apiResponse.getStatusCode());
        if(httpStatus == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }   

    @GetMapping("/getbookings")
    public ResponseEntity<ApiRes<List<BookingDto>>> getUserBookings(@RequestParam(value = "userid", required = false) String userid) {
        ApiRes<List<BookingDto>> apiResponse = bookingService.getUserBookings(userid);
        HttpStatus status = HttpStatus.resolve(apiResponse.getStatusCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(apiResponse);
    }

    // Depriciated used when I created JWT flow for login and register
      
    // @PostMapping("/auth/register")  
    // public ResponseEntity<ApiRes<String>> registerUser(@RequestBody UserCredential user) {
    //     ApiRes<String> apiResponse = userCredServiceImpl.registerUser(user);
    //     HttpStatus httpStatus = HttpStatus.resolve(apiResponse.getStatusCode());
    //     if(httpStatus == null) {
    //         httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    //     }
    //     return ResponseEntity.status(httpStatus).body(apiResponse);
    // }

    // @PostMapping("/auth/login")
    // public ResponseEntity<ApiRes<String>> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
    //     ApiRes<String> apiResponse = userCredServiceImpl.loginUser(loginRequestDto);
    //     HttpStatus httpStatus = HttpStatus.resolve(apiResponse.getStatusCode());
    //     if(httpStatus == null) {
    //         httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    //     }
    //     return ResponseEntity.status(httpStatus).body(apiResponse);
    // }

}
