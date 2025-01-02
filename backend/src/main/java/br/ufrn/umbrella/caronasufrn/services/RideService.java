package br.ufrn.umbrella.caronasufrn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.umbrella.caronasufrn.dtos.RideDTO;
import br.ufrn.umbrella.caronasufrn.entities.User;
import br.ufrn.umbrella.caronasufrn.entities.Ride;
import br.ufrn.umbrella.caronasufrn.models.classes.ApiResponse;
import br.ufrn.umbrella.caronasufrn.repositories.RideRepository;
import br.ufrn.umbrella.caronasufrn.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideService {
  private final RideRepository rideRepository;
  private final UserRepository userRepository;

  public ApiResponse<List<Ride>> findAll() {
    List<Ride> rides = new ArrayList<>();
    rides = rideRepository.findAll();

    ApiResponse<List<Ride>> response = new ApiResponse<>();
    return response.of(HttpStatus.ACCEPTED, "Rides fetched.", rides);
  }

  @Transactional
  public ApiResponse<Ride> save(RideDTO rideDTO) {
    ApiResponse<Ride> response = new ApiResponse<>();

    Ride ride = new Ride();
    Optional<User> user = userRepository.findById(rideDTO.driverId());
    user.ifPresentOrElse(
      value -> {
        BeanUtils.copyProperties(rideDTO, ride);

        if (ride.getArrivalTime().isBefore(ride.getDepartureTime())) {
          response.of(
            HttpStatus.BAD_REQUEST,
            "Arrival time must be after departure time.",
            null);
        } else {
          ride.setDriver(value);
          Ride savedRide = rideRepository.save(ride);
          response.of(HttpStatus.CREATED, "Ride was created.", savedRide);
        }

      },
      () -> {
          response.of(HttpStatus.NOT_FOUND, "Driver not found", null);
      }
    );

    return response;
  }
}
