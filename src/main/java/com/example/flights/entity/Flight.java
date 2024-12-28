package com.example.flights.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Flight")
public class Flight {

    @Id
   // @NotNull(message = "Flight ID cannot be null")
    @NotEmpty(message = "Flight ID cannot be empty")
    private String flightId;

    String flightNo;

   // @NotNull(message = "Flight name cannot be null")
    @NotEmpty(message = "Flight name cannot be empty")
    private String flightName;

   // @NotNull(message = "Source cannot be null")
    @NotEmpty(message = "Source cannot be empty")
    private String source;

  //  @NotNull(message = "Destination cannot be null")
    @NotEmpty(message = "Destination cannot be empty")
    private String destination;

   // @NotNull(message = "Price cannot be null")
    @NotEmpty(message = "Price cannot be empty")
    private String price;

    private int seats;

    @NotNull(message = "Duration cannot be null")
  //  @NotEmpty(message = "Duration cannot be empty")
    private LocalTime duration;

    @NotNull(message = "DateTime cannot be null")
    private LocalDateTime dateTime;

  //  @NotNull(message = "Status cannot be null")
    @NotEmpty(message = "Status cannot be empty")
    private String status;

  //  @NotNull(message = "Airline cannot be null")
    @NotEmpty(message = "Airline cannot be empty")
    private String airline;

    private String stops;
    private LocalTime layoverTime;
    private String originAirport;

  //  @NotNull(message = "Destination Airport cannot be null")
    @NotEmpty(message = "Destination Airport cannot be empty")
    private String destinationAirport;

  //  @NotNull(message = "Origin Airport Terminal cannot be null")
    @NotEmpty(message = "Origin Airport Terminal cannot be empty")
    private String originAirportTerminal;

  //  @NotNull(message = "Destination Airport Terminal cannot be null")
    @NotEmpty(message = "Destination Airport Terminal cannot be empty")
    private String destinationAirportTerminal;

    private int checkInBag;
    private int cabinBag;

    @NotNull(message = "Arrival Time cannot be null")
    private LocalTime arrivalTime;

    @NotNull(message = "Departure Time cannot be null")
    private LocalTime departureTime;

    @NotNull(message = "Departure Date cannot be null")
    private LocalDate departureDate;

    @NotNull(message = "Arrival Date cannot be null")
    private LocalDate arivalDate;

    private String flightClass;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Layovers> layovers;
}
