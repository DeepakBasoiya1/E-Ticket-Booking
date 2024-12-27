package com.example.flights.services;

import com.example.flights.dto.ApiRes;
import com.example.flights.entity.User;

public interface UserService {

   public ApiRes<String> registerUser(User user);
    
} 
