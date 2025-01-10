package br.ufrn.umbrella.caronasufrn.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	@GetMapping("/all")
	public ResponseEntity<ApiResponse<List<User>>> findAll() {
		ApiResponse<List<User>> response = userService.findAll();

		return ResponseEntity.status(response.getStatus()).body(response);
		
	}

	@Operation(
		summary = "Busca os dados do usuário autenticado. [REQUER AUTENTICAÇÃO BEARER]",
		description = "Retorna o objeto do usuário referente ao token."
	)
	@GetMapping
	public ResponseEntity<ApiResponse<User>> findMyUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		ApiResponse<User> response = userService.findMyUser(userDetails.getUsername());

		return ResponseEntity.status(response.getStatus()).body(response);
		
	}

	@Operation(
		summary = "Atualiza a imagem do usuário. [REQUER AUTENTICAÇÃO BEARER]",
		description = "Pega o usuário do token e atualiza/remove a imagem dele. A imagem deve ser enviada num FormData "+
		"como um File num campo de nome 'image_file'. Para excluir a imagem, deixe o campo 'image_file' vazio."
	)
	@PatchMapping
	public ResponseEntity<ApiResponse<User>> patchImage(
		@RequestPart("image_file") MultipartFile productFile
	){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		ApiResponse<User> response = userService.patchUserImage(userDetails.getUsername(), productFile);

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
