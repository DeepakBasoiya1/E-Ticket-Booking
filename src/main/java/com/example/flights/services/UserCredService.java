package com.example.flights.services;

import com.example.flights.dto.ApiRes;
import com.example.flights.dto.LoginRequestDto;
import com.example.flights.entity.UserCredential;

public interface UserCredService {

    public ApiRes<String> registerUser(UserCredential user);
    public ApiRes<String> loginUser(LoginRequestDto loginRequestDto);
    
} 
