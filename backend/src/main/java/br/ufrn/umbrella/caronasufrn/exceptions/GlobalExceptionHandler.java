package br.ufrn.umbrella.caronasufrn.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.JWTCreationException;

import br.ufrn.umbrella.caronasufrn.models.classes.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			@NonNull MethodArgumentNotValidException ex, 
		    @NonNull HttpHeaders headers, 
		    @NonNull HttpStatusCode status, 
		    @NonNull WebRequest request)
	{
		Map<String, List<String>> body = new HashMap<>();
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());
		
		body.put("errors", errors);
	
	    ApiResponse<Map<String, List<String>>> response = new ApiResponse<>();
	    
	    response.setStatus(HttpStatus.BAD_REQUEST);
	    response.setData(body);
	    response.setMessage("Request argument error.");
	    
	    return ResponseEntity.status(response.getStatus()).body(response);
	}	

	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		ApiResponse<String> response = new ApiResponse<>();

        if (ex.getMessage().contains("duplicate key value violates unique constraint")) {
			response.of(HttpStatus.BAD_REQUEST, "Email already in use.", null);
            return ResponseEntity.status(response.getStatus()).body(response);
        }
    
        response.of(HttpStatus.INTERNAL_SERVER_ERROR, "Data integrity error.", null);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

	@ExceptionHandler(JWTCreationException.class)
	public ResponseEntity<Object> handleJWTCreationException(JWTCreationException ex){
		ApiResponse<String> response = new ApiResponse<>();

		response.of(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating token.", null);

		return ResponseEntity.status(response.getStatus()).body(response);
	} 
}
