package com.example.flights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flights.dto.ApiRes;
import com.example.flights.entity.User;
import com.example.flights.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

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

}
