package com.example.flights.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightDto {

      private String flightId;
      private String flightName;
      private LocalDateTime date;
      private String source;
      private String destination;
      private LocalTime departureTime;
      private LocalTime arrivalTime;
      private String stops;
      private String price;
      private LocalTime duration;      
            

}
