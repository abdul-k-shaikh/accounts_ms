package com.eazybytes.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.eazybytes.accounts.dto.AccountsContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value= {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info= @Info(
				title="Accounts Microservices REST API Documentation",
				description="EazyBank Accounts microservices REST API Documentation",
				version="v1",
				contact = @Contact(
						name="Abdul K Shaikh",
						email="abshaikh9786@gmail.com"
						),
				license=@License(
						name="Apache2.0",
						url="#"
						)
				),
		externalDocs=@ExternalDocumentation(
				description="EazyBank Account microservices REST API Documentation",
				url="#"
				)
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
