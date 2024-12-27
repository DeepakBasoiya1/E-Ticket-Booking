package com.example.flights.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.flights.entity.Flight;

import jakarta.persistence.LockModeType;

@Repository
public interface FlightRepo extends JpaRepository<Flight, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT f FROM Flight f WHERE f.id = :flightId")
    public Flight findByIdWithLock(@Param("flightId") String flightId);


    @Query("SELECT f FROM Flight f WHERE " +
       "LOWER(f.source) = LOWER(:source) AND " +
       "LOWER(f.destination) = LOWER(:destination) AND " +
       "f.dateTime BETWEEN :startDate AND :endDate AND " +
       "LOWER(f.flightclass) = LOWER(:flightclass)")
    public List<Flight> findFlightsByFilter(
        @Param("source") String source,
        @Param("destination") String destination,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("flightclass") String flightclass
    );


   
    @Query("SELECT f FROM Flight f JOIN FETCH f.layovers WHERE f.flightid = :flightId")
Optional<Flight> findFlightWithLayovers(@Param("flightId") String flightId);

}



