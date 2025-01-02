package br.ufrn.umbrella.caronasufrn.repositories;

import br.ufrn.umbrella.caronasufrn.models.enums.Status;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.ufrn.umbrella.caronasufrn.entities.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, String>, JpaSpecificationExecutor<Ride> {
  Optional<Ride> findByDriverId(String driverId);

  List<Ride> findByPassengersId(String userId);

  List<Ride> findByDate(LocalDate date);

  List<Ride> findByDepartureTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<Ride> findByStatus(Status status);

  List<Ride> findByDriverIdAndDate(String driverId, LocalDate date);

  List<Ride> findByPassengersIdAndStatus(String userId, Status status);

  List<Ride> findByToUfrn(Boolean toUfrn);

  List<Ride> findByFromAndTo(String fromLocation, String toLocation);

  List<Ride> findByPriceLessThan(Float price);

  List<Ride> findByMaxPassengersQuantity(Integer maxPassengersQuantity);
}
