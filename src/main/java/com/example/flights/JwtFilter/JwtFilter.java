// package com.example.flights.JwtFilter;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.OncePerRequestFilter;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;

// import java.io.IOException;
// import java.util.Arrays;
// import java.util.List;
// import java.util.stream.Collectors;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// public class JwtFilter extends OncePerRequestFilter {

//     private static final String SECRET_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlxw1jMdkkZGwtHg5vp6c\\n" + //
//                 "58254zf0/34PJ+U70Hpn84lHBjcYTUwyXQ/exLClIFVK2lVDIgHPQ+vKsmoTaD5S\\n" + //
//                 "ouHvRRXyvgEd8tBirbXzdYCpv8NoJKUIOA4tZ5VZdlkWeGMVKKDcL/TjfPSFBh1u\\n" + //
//                 "1UjyuR/UGl9KBF1ffYrYWqT9ul4gvcZhxyyL++MkCTvYquA5+ftuUmsyxYCaIbFG\\n" + //
//                 "C+t3QSxzatg2/YpQrYPblItHi6Y7lhYYwbugSj7JK/GR7kSR3vn/OpwmuBP4c6D7\\n" + //
//                 "ogNDDdp8E9CLSfvxGJ40AwRIA5UlPdE6UFzTuwZRyzIk4V6rKHeZ3Olv1AFtC9yn\\n" + //
//                 "uQIDAQAB";

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
          
//            String authorizationHeader = request.getHeader("Authorization");
//            logger.info("Authorization Header{}: "+authorizationHeader);
//            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
//                String jwt = authorizationHeader.substring(7);
//                try{
//                 Claims claims = Jwts.parser()
//                 .setSigningKey(SECRET_KEY.getBytes())
//                 .build()
//                 .parseClaimsJws(jwt)
//                 .getBody();
            
//                  List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
//          .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//          .collect(Collectors.toList());

//                 log.info("claims: {}" + claims.toString());
//                 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                 claims.getSubject(), null, null);
//                 authenticationToken.setDetails(claims);
//                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//                 catch(Exception e){
//                     log.error("invalid token", e.getMessage());
//                     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                     response.getWriter().write("{\"error\": \"Invalid Token\"}");
//                      response.sendError(401);
//                      return ;
//                 }
//            }
//         log.info("Request URI: {}", request.getRequestURI());   
//         filterChain.doFilter(request, response);
//     }

// }
