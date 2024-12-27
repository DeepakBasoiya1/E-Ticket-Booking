package com.example.flights.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRes<T> {
    
    private int statusCode; 
    private boolean status;
    private String message;
    private T data;
   
}
