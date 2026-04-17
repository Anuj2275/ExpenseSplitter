package com.learning.ExpenseSplitter.service;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET = "t3is083ish0htjistj0arandpm0wifjkery0w";
//    JWT is NOT encrypted → it is signed
//Meaning:
//Anyone can read payload
//But cannot modify (signature protects)
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *60) ) // +1 hr
                .signWith(
                        Keys.hmacShaKeyFor(SECRET.getBytes()),
                        SignatureAlgorithm.HS256
                ) // here we always need two things key and a signature algo, This protects token from being modified
                .compact();
    }


//    Token → decode → get payload → return subject (email)
    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
//✔ valid token → true
//❌ expired / tampered → false
    public boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
