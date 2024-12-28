package br.ufrn.umbrella.caronasufrn.controllers;

import org.springframework.web.bind.annotation.RestController;
import br.ufrn.umbrella.caronasufrn.configs.auth.JwtService;
import br.ufrn.umbrella.caronasufrn.dtos.AuthDTO;
import br.ufrn.umbrella.caronasufrn.entities.User;
import br.ufrn.umbrella.caronasufrn.models.classes.JwtResponse;
import br.ufrn.umbrella.caronasufrn.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@Tag(name="Autenticação", description = "Endpoints de autenticação.")
public class AuthController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    @Operation(
        summary = "Realiza o login.",
        description = "Realiza um login e recebe um JWT."
    )
    public ResponseEntity<JwtResponse> login(@RequestBody AuthDTO authDTO) {
        User user = userRepository.findByEmail(authDTO.email())
            .orElseThrow(()->new EntityNotFoundException("User with given email not found."));

        if(passwordEncoder.matches(authDTO.password(), user.getPasswordHash())){
            String token = jwtService.createToken(user);
            return ResponseEntity.ok(new JwtResponse("Logged.",token, user.getName(), user.getRole().value));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JwtResponse("Login error",null, null, null));
    }
}
