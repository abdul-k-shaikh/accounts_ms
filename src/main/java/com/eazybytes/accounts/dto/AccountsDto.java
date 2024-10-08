package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold account information")
public class AccountsDto {
	
	
	@NotEmpty(message="AccountNumber can not be null or empty")
	@Pattern(regexp="(^$|[0-9]{10})", message="AccountNumber must be 10digits")
	@Schema(description = "Account number of Eazy Bank", example = "3421567898")
	private Long accountNumber;
	
	
	@NotEmpty(message="AccountType can not be a null or empty")
	@Schema(description = "Account type of Eazy Bank account", example = "Savings")
	private String accountType;
	
	@NotEmpty(message="branchAddress can not be a null or empty")
	@Schema(description = "Eazy Bank Branch address", example = "123 Mumbai")
	private String branchAddress;
}
