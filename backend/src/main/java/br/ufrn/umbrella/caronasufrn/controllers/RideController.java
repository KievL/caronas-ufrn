package br.ufrn.umbrella.caronasufrn.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.umbrella.caronasufrn.dtos.RideDTO;
import br.ufrn.umbrella.caronasufrn.entities.Ride;
import br.ufrn.umbrella.caronasufrn.models.classes.ApiResponse;
import br.ufrn.umbrella.caronasufrn.services.RideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
@Tag(name = "ride", description = "Endpoints do ride.")
public class RideController {

	private final RideService rideService;

	@Operation(
		summary = "Busca corridas. [REQUER AUTENTICAÇÃO BEARER]",
		description = "Busca corridas com determinada specification. Se nenhuma specification for dada,  "+
		"todas as corridas serão retornadas."
	)
	@GetMapping
	public ResponseEntity<ApiResponse<List<Ride>>> findAll() {
		ApiResponse<List<Ride>> response = rideService.findAll();

		return ResponseEntity.status(response.getStatus()).body(response);
		
	}

	@Operation(
		summary = "Registra uma nova corrida.",
		description = "Registra uma nova corrida e, caso tenha sucesso, o objeto da nova corrida é retornado."
	)
	@PostMapping
	public ResponseEntity<ApiResponse<Ride>> save(@Valid @RequestBody RideDTO rideDTO) {
		
		ApiResponse<Ride> response = rideService.save(rideDTO);
		
		return ResponseEntity.status(response.getStatus()).body(response);
	}


	
}
