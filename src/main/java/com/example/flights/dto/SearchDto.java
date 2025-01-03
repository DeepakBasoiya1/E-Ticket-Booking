package com.example.flights.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {

    @NotNull(message = "Source cannot be null")
    @NotEmpty(message = "Source cannot be empty")
    private String source;

    @NotNull(message = "Destination cannot be null")
    @NotEmpty(message = "Destination cannot be empty")
    private String destination;

    @NotNull(message = "Date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime departureDate;

    private String flightClass = "economy";
}
