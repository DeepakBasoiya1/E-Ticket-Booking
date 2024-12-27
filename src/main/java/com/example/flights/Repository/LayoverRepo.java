package com.example.flights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flights.entity.Layovers;

@Repository
public interface LayoverRepo extends JpaRepository<Layovers, String> {

}
