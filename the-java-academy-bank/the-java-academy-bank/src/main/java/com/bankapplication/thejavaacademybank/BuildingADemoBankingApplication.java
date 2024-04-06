package com.bankapplication.thejavaacademybank;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "The Spring Boot Bank App",
				description = "Backend REST APIs for Bank",
				version = "v1.0",
				contact = @Contact(
						name = "Mohit Sharma",
						email = "mohit10barza@gmail.com",
						url = "https://github.com/mohitshrma/Banking_App-SpringBoot-"
				),
				license = @License(
						name = "Bank App",
						url = "https://github.com/mohitshrma/Banking_App-SpringBoot-"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "The Spring Boot Bank App Documentation",
				url = "https://github.com/mohitshrma/Banking_App-SpringBoot-"
		)
)
public class BuildingADemoBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildingADemoBankingApplication.class, args);
	}

}
