package com.example.flights.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingReqDto {

    @NotNull(message = "User ID cannot be null")
    @NotEmpty(message = "User ID cannot be empty")
    private String userId;

    @NotNull(message = "Flight ID cannot be null")
    @NotEmpty(message = "Flight ID cannot be empty")
    private String flightId;

    @NotNull(message = "Passanger Name cannot be null")
    @NotEmpty(message = "Passanger Name cannot be empty")
    private String passangerName;

    @NotNull(message = "Passanger Email cannot be null")
    @NotEmpty(message = "Passanger Email cannot be empty")
    @Email(message = "Invalid Email")
    private String passangerEmail;
    
}
