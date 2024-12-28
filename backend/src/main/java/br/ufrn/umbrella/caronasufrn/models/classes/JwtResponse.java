package br.ufrn.umbrella.caronasufrn.models.classes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String message;
    private String token;
    private String name; 
    private String role;   
}
