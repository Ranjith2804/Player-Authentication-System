package com.example.Player.Authentication.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

import io.jsonwebtoken.Jwts;

@Service
public class JwtServvice {
    public static final String Secret="ai122y1827719276817211618672bdaugbdaihdioahd11221";
    public String generatetoken(String userName){
        Map<String,Object> claims = new HashMap<>();
        return createtoken(claims,userName);
    }
    private String createtoken(Map<String,Object> claims,String userName){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
    }
    private Key getSignKey(){
        byte[] keyb= Decoders.BASE64.decode(Secret);
        return Keys.hmacShaKeyFor(keyb);
    }
    public String extractUsername(String token){
        return extractclaim(token,Claims::getSubject);
    }
    public Date extractexpiration(String token){
        return extractclaim(token,Claims::getExpiration);
    }
    public <T> T extractclaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpired(String token){
        return extractexpiration(token).before(new Date());
    }
    public Boolean validatetoken(String token,UserDetails userDetails){
        final String username=extractUsername(token);
        return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
