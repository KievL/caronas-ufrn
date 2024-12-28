package br.ufrn.umbrella.caronasufrn.configs.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.ufrn.umbrella.caronasufrn.entities.User;

@Service
public class JwtService {
    
    @Value("${secret.key}")
    private String secret;

    public String createToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String token = JWT.create()
            .withIssuer("umbrella-caronas")
            // .withIssuedAt(generateCreationDate()) problema com fuso horário dos containers
            .withSubject(user.getEmail())
            .withClaim("role", user.getRole().value)
            .withClaim("name", user.getName())
            .sign(algorithm);

        return token;
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("umbrella-caronas")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    // private Instant generateCreationDate() {
    //     return LocalDateTime.now().toInstant(ZoneOffset.of("-03:00"));
    // }
}
