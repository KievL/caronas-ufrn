package br.ufrn.umbrella.caronasufrn.dtos;

import br.ufrn.umbrella.caronasufrn.models.enums.Status;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record RideDTO(
  @NotEmpty(message = "Origin is required.")
  @Size(min = 2, max = 100, message = "Length of origin must be between 2 and 100 characters.")
  String from,

  @NotEmpty(message = "Destination is required.")
  @Size(min = 2, max = 100, message = "Length of destination must be between 2 and 100 characters.")
  String to,

  @NotNull(message = "Price is required.")
  @Min(value = 0, message = "Price must be at least 0.")
  Float price,

  @NotNull(message = "Ride date and time is required.")
  @Future(message = "Ride date and time must be in the future.")
  LocalDateTime date,

  @NotNull(message = "Departure time is required.")
  @Future(message = "Departure time must be in the future.")
  LocalDateTime departureTime,

  @NotNull(message = "Arrival time is required.")
  LocalDateTime arrivalTime,

  @NotNull(message = "Available seats is required.")
  @Min(value = 1, message = "Available seats must be at least 1.")
  @Max(value = 5, message = "Available seats must be less than or equal to 5.")
  Integer maxPassengersQuantity,

  @NotEmpty(message = "Driver ID is required.")
  String driverId,

  @NotNull(message = "To UFRN is required.")
  Boolean toUfrn,

  @NotNull(message = "Status is required.")
  Status status
) {

}

