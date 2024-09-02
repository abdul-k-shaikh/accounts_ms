package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "CustomerDetails", description = "Schema to hold Customer, Account, Cards and Loans information")
public class CustomerDetailsDto {
	@Schema(description = "Name of the customer", example = "John D")
	@NotEmpty(message = "Name can not be empty")
	@Size(min = 5, max = 30, message = "The name of the customer should be between 5-30")
	private String name;

	@Schema(description = "email of the customer", example = "John@gmail.com")
	@NotEmpty(message = "Email can not be empty or null")
	@Email(message = "Email should be a valid address")
	private String email;

	@Schema(description = "Mobile number of the customer", example = "9078654239")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must 10 digits")
	private String mobileNumber;

	@Schema(description = "Account details of the customer")
	private AccountsDto accountsDto;

	@Schema(description = "Loans details of the customer")
	private LoansDto loansDto;

	@Schema(description = "Cards details of the customer")
	private CardsDto cardsDto;

}
