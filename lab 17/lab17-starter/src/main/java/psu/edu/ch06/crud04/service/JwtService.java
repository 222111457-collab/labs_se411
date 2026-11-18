
package psu.edu.ch06.crud04.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final long EXPIRATION_MS = 60 * 60 * 1000;
    private String secret;

    public JwtService() {
        try {
            SecretKey sk = KeyGenerator
                .getInstance("HmacSHA256")
                .generateKey();

            secret = Base64.getEncoder()
                .encodeToString(sk.getEncoded());

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                "Cannot generate JWT signing key", e);
        }
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
            .claims().add(claims)
            .subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(
                System.currentTimeMillis() + EXPIRATION_MS))
            .and()
            .signWith(getKey())
            .compact();
    }

    public boolean validateToken(
            String token,
            org.springframework.security.core.userdetails.UserDetails userDetails) {

        return extractUsername(token)
            .equals(userDetails.getUsername())
            && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(secret));
    }

    private <T> T extractClaim(
            String token,
            Function<Claims, T> resolver) {

        return resolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(
            token, Claims::getExpiration).before(new Date());
    }
}
