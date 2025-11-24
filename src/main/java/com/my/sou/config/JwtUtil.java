package com.my.sou.config;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

  private final static String SECRET = "w2,0yObX7ns.ew$McWQd=TGVwL:BrN52";
  private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
  private final long EXPIRATION = 1000 * 60 * 60;

  public String generateToken(org.springframework.security.core.userdetails.User user) {
    String roles = user.getAuthorities().stream().map(auth -> auth.getAuthority())
        .collect(Collectors.joining(","));

    return Jwts.builder().setSubject(user.getUsername()).claim("roles", roles)
        .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .signWith(KEY, SignatureAlgorithm.HS256).compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  public String getEmail(String token) {
    return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody()
        .getSubject();
  }

  public String getRoles(String token) {
    return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody()
        .get("roles", String.class);
  }
}
