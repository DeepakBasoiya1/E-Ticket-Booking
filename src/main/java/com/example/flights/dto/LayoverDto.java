package com.example.flights.dto;

import java.time.LocalTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LayoverDto {

    private String flightName;
    private String layoverLocation;
    private LocalTime layoverDuration;
    private LocalTime layoverStartTime;
    private LocalTime layoverEndTime;
    private String layoverDepartureAirport;
    private String layoverArivalAirport;

}
