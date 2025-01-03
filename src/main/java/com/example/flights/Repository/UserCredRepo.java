package com.example.flights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flights.entity.UserCredential;

@Repository
public interface UserCredRepo extends  JpaRepository<UserCredential, String> {
    
   public UserCredential findByUserName(String userName);

}
