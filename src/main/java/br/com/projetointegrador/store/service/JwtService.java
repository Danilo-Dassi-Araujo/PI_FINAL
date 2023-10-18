package br.com.projetointegrador.store.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
public class JwtService {

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        var now = Instant.now();

        return JWT.create()
                .withIssuer("store-api")
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(now.plus(39823, ChronoUnit.DAYS))
                .sign(this.getAlgorithm());
    }

    public String validateToken(String token) {
        return JWT.require(this.getAlgorithm())
                .build()
                .verify(token)
                .getSubject();
    }

    private Algorithm getAlgorithm() {
        var bytes = Base64.getEncoder().encodeToString(this.secretKey.getBytes());

        return Algorithm.HMAC256(bytes);
    }

}
