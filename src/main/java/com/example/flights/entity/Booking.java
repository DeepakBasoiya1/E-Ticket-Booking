package com.example.flights.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    String bookingId;

    private String userId;
    private String userName;
    private String userEmail;
    private String price;
    private LocalDate bookingDate;
    private LocalDate doj;
    private String flightName;
    private String source;
    private String destinationCity;
    private String flightId;
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

}
