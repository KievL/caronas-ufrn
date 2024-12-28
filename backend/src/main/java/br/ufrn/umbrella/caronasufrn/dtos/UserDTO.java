package br.ufrn.umbrella.caronasufrn.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(
    @NotEmpty(message="Name is required.")
    @Size(min=2, max=100, message="Length of name must be between 2 and 100 characters.")
    String name,

    @NotEmpty(message = "Email is required.")
    @Email(message = "Email is invalid.")
    String email,

    @NotEmpty(message = "Password hash is required.")
    @Size(min=8, max=50, message = "Password must be between 8 and 50 characters.")
    String passwordHash,

    @NotEmpty(message = "Course is required.")
    @Size(min = 2, max = 100, message = "Course string must be between 2 and 100 characters.")
    String course,

    @NotEmpty(message = "Enrollment number is required")
    @Size(min=4, max=20, message = "Enrollment number must be between 4 and 20 characters.")
    String enrollmentNumber

) {
    
}
