// package com.example.flights.services_impl;



// import java.util.Date;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.example.flights.dto.ApiRes;
// import com.example.flights.dto.AuthResponseDto;
// import com.example.flights.dto.LoginRequestDto;
// import com.example.flights.entity.UserCredential;
// import com.example.flights.repository.UserCredRepo;
// import com.example.flights.services.UserCredService;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Service
// public class UserCredServiceImp implements UserCredService {

//     @Autowired
//     UserCredRepo userCredRepo;

//     @Override
//     public ApiRes<String> registerUser(UserCredential user) {
//     try{
//         String hashPassword = new BCryptPasswordEncoder().encode(user.getUserPassword());
//         user.setUserPassword(hashPassword);
//         userCredRepo.save(user);
//         return new ApiRes<String>(200, true,"User registered successfully", null);
// }
//     catch(Exception e){
//         return new ApiRes<String>(500, false, "Internal Server Error", null);
//     }
// }

//     @Override
//     public ApiRes<String> loginUser(LoginRequestDto loginRequestDto) {
//         try {
//             UserCredential user = userCredRepo.findByUserName(loginRequestDto.getUserName());
//             log.info("User:{}" + user);
//             if(user == null) {
//                 return new ApiRes<String>(404, false, "User not found", null);
//             }
//             AuthResponseDto authResponseDto = new AuthResponseDto();
//             if(new BCryptPasswordEncoder().matches(loginRequestDto.getUserPassword(), user.getUserPassword())) {
//                 String token = Jwts.builder()
//                 .setSubject(user.getUserName())
//                 .claim("id", user.getId())
//                 .claim("roles", user.getRole())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 30))
//                 .signWith(SignatureAlgorithm.HS256, "myVerySecureKeyWithSufficientLength123456".getBytes())
//                 .compact();
//                 authResponseDto.setToken(token);
//                 log.info("Token:{}" + token);
//                 return new ApiRes<String>(200, true, "Login successful", authResponseDto.getToken());
//             }
//             return new ApiRes<String>(401, false, "Invalid credentials", null);
//         } catch (Exception e) {
//             log.info(e.getMessage());
//             return new ApiRes<String>(500, false, "Internal Server Error", "exception");
//         }
//     }

// }
