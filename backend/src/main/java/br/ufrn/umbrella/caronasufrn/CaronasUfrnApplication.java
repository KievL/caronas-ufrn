package br.ufrn.umbrella.caronasufrn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title = "Caronas UFRN", version = "1", description = "Caronas UFRN API endpoints."))
public class CaronasUfrnApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaronasUfrnApplication.class, args);
	}

}
