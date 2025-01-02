package br.ufrn.umbrella.caronasufrn.entities;

import br.ufrn.umbrella.caronasufrn.models.enums.Status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rides")
public class Ride {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne
  @JoinColumn(name = "driver_id", nullable = false)
  private User driver;

  @Column(name = "max_passengers_quantity", nullable = false)
  private Integer maxPassengersQuantity;

  @ManyToMany
  @JoinTable(name = "ride_passengers", joinColumns = @JoinColumn(name = "ride_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> passengers;

  @ManyToMany
  @JoinTable(name = "ride_requesters", joinColumns = @JoinColumn(name = "ride_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> requesters;

  @Column(name = "to_ufrn", nullable = false)
  private Boolean toUfrn;

  @Column(name = "from_location", nullable = false)
  private String from;

  @Column(name = "to_location", nullable = false)
  private String to;

  @Column(nullable = false)
  private Float price;

  @Column(name = "departure_time", nullable = false)
  private LocalDateTime departureTime;

  @Column(name = "arrival_time", nullable = false)
  private LocalDateTime arrivalTime;

  @Column(nullable = false)
  private LocalDateTime date;

  @Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
  private Status status;
}
