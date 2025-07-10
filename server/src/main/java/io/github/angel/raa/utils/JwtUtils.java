package io.github.angel.raa.utils;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.github.angel.raa.service.impl.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationMs:86400000}")
    private String jwtExpirationMs;

    public @NonNull SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Map<String, String> claims, Authentication authentication) {
        UserDetailsImpl detailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .subject(detailsImpl.getUsername())
                .claims(claims)
                .claim("username", detailsImpl.getUsername())
                .claim("roles", detailsImpl.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpirationMs)))

                .signWith(getSigningKey())

                .compact();
    }

    public String generateToken(Authentication authentication) {
        UserDetailsImpl detailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .subject(detailsImpl.getUsername())
                .claim("username", detailsImpl.getUsername())
                .claim("roles", detailsImpl.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpirationMs)))

                .signWith(getSigningKey())

                .compact();
    }

    public String generateToken(UserDetails details) {
        return Jwts.builder()
                .subject(details.getUsername())
                .claim("username", details.getUsername())
                .claim("roles", details.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpirationMs)))

                .signWith(getSigningKey())

                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = extractExpired(token);
        return expiration.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpired(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

}
