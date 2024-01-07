package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {

    @Value("${spring.application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${spring.application.security.jwt.expiration}")
    private Integer expirationMinutes;
    public String extractUsername(
            @NotNull String token
    ) {

        return JWT
                .require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token)
                .getSubject();
    }

    public Map<String, Claim> extractAll(
            @NotNull String token
    ) {
        return JWT
                .require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token)
                .getClaims();
    }

    public boolean isTokenValid(
            @NotNull String token,
            @NotNull UserDetails user
    ) {

        final String username = extractUsername(token);

        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(
            @NotNull String token
    ) {
        return extractTokenExpiration(token).before(new Date());
    }

    public Date extractTokenExpiration(
            @NotNull String token
    ) {
        return JWT
                .require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token)
                .getExpiresAt();
    }

    public String generateToken(
            @NotNull User user
    ) {
        return JWT
                .create()
                    .withSubject(user.getUsername())
                    .withClaim("role", user.getRole().name())
                    .withIssuedAt(Instant.from(LocalDateTime.now().atZone(ZoneId.systemDefault())))
                    .withExpiresAt(Instant.from(LocalDateTime.now().plusMinutes(expirationMinutes).atZone(ZoneId.systemDefault())))
                .sign(Algorithm.HMAC256(secretKey));
    }

}
