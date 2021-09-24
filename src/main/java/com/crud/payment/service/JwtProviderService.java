package com.crud.payment.service;

import com.crud.payment.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtProviderService {

    private final String jwtSecret;
    private final Clock clock;

    public JwtProviderService(@Value("$(jwt.secret)") String jwtSecret,
                              Clock clock) {
        this.jwtSecret = jwtSecret;
        this.clock = clock;
    }

    public String generateToken(User user) {
        Date date = Date.from(clock.instant()
                .plus(1, ChronoUnit.DAYS)
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return Jwts.builder()
                .setSubject(user.getName())
                .setExpiration(date)
                .claim("id", user.getId())
                .claim("password", user.getPassword())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public User getUserFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        final String name = claims.getSubject();
        final Long id = (Long) claims.get("id");
        final String password = (String) claims.get("password");
        return new User(id, name, password);
    }
}