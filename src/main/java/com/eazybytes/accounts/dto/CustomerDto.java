package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
	
	@NotEmpty(message="Name can not be empty")
	@Size(min=5, max=30, message="The name of the customer should be between 5-30")
    private String name;
	
	@NotEmpty(message="Email can not be empty or null")
	@Email(message="Email should a valid address")
	private String email;
	
	@Pattern(regexp="(^$|[0-9]{10})", message="mobile number must 10 digits")
	private String mobileNumber;
	
	private AccountsDto accountsDto;
}
