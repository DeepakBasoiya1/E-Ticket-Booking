package com.example.flights.services_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flights.dto.ApiRes;
import com.example.flights.entity.User;
import com.example.flights.repository.UserRepo;
import com.example.flights.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    public ApiRes<String> registerUser(User user) {
        try {
            User newUser = userRepo.findByEmail(user.getEmail());
            if (newUser != null) {
                return new ApiRes<>(400, false, "User already registered with this Email already exists", null);
            }
            userRepo.save(user);
            return new ApiRes<>(200, true, "User registered successfully with", user.getEmail());
        } catch (Exception e) {
            return new ApiRes<>(500, false, "An error occurred while registering the user: " + e.getMessage(), null);
        }
    }





    
}
