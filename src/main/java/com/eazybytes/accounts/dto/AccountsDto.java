package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
	@NotEmpty(message="AccountNumber can not be null or empty")
	@Pattern(regexp="(^$|[0-9]{10})", message="AccountNumber must be 10digits")
	private Long accountNumber;
	
	@NotEmpty(message="AccountType can not be a null or empty")
	private String accountType;
	
//	private String email;
	
	@NotEmpty(message="branchAddress can not be a null or empty")
	private String branchAddress;
}
