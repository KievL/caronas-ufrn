package br.ufrn.umbrella.caronasufrn.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.umbrella.caronasufrn.dtos.UserDTO;
import br.ufrn.umbrella.caronasufrn.entities.User;
import br.ufrn.umbrella.caronasufrn.models.classes.ApiResponse;
import br.ufrn.umbrella.caronasufrn.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "Endpoints do User.")
public class UserController {

	private final UserService userService;

	@Operation(
		summary = "Busca usuários. [REQUER AUTENTICAÇÃO BEARER]",
		description = "Busca usuários com determinada specification. Se nenhuma specification for dada,  "+
		"todos usuários serão retornados."
	)
	@GetMapping
	public ResponseEntity<ApiResponse<List<User>>> findAll() {
		ApiResponse<List<User>> response = userService.findAll();

		return ResponseEntity.status(response.getStatus()).body(response);
		
	}

	@Operation(
		summary = "Registra um novo usuário.",
		description = "Registra um novo usuário e, caso tenha sucesso, o objeto do novo usuário é retornado."
	)
	@PostMapping
	public ResponseEntity<ApiResponse<User>> save(@Valid @RequestBody UserDTO userDTO) {
		
		ApiResponse<User> response = userService.save(userDTO);
		
		return ResponseEntity.status(response.getStatus()).body(response);
	}


	
}
