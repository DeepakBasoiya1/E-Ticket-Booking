package com.example.flights.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flights.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, String> {

    // @Query("SELECT b FROM Booking b WHERE b.userid=:userid")
    // List<Booking> findByUserUserId(
    //              @Param("bookingid") String bookingid);
}
