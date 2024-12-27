package com.example.flights.entity;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "layovers")
public class Layovers {

    @Id
    private String layoverId;
    private String flightName;
    private String layoverLocation;
    private LocalTime layoverDuration;
    private LocalTime layoverStartTime;
    private LocalTime layoverEndTime;
    private String layoverDepartureAirport;
    private String layoverArivalAirport;
    private String terminalInfo;



    @Column(insertable = false, updatable = false)
    @NotNull(message = "Flight ID cannot be null")
    @NotEmpty(message = "Flight ID cannot be empty")
    private String flightId;

    @ManyToOne()
    @JoinColumn(name = "flightId")
    @JsonIgnore
    private Flight flight;
    

}
