package com.eazybytes.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

/**
 * @author AbdulShaikh
 */

@Tag(name = "CRUD REST APIS for Accounts MS in EazyBank", description = "CRUD REST APIs in EazyBank to create, update, delete and fetch account details")
@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class AccountsController {
	private final IAccountsService iAccountsService;

	public AccountsController(IAccountsService iAccountsService) {
		this.iAccountsService = iAccountsService;
	}

	@Value("${build.version}")
	private String buildVersion;

	@Autowired
	private Environment environment;

	@Autowired
	private AccountsContactInfoDto accountsContactInfoDto;

	@Operation(summary = "Create Account REST API", description = "REST API to create new customer & Account inside EazyBank")
	@ApiResponse(responseCode = "201", description = "HTTP Status Created")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		iAccountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED) // line 23 at header level status created
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));

	}

	@Operation(summary = "Fetch Account REST API", description = "REST API to create new customer & Account inside EazyBank")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status ok"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error") })
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must 10 digits") String mobileNumber) {
		CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);

	}

	@Operation(summary = "Update Account REST API", description = "REST API to create new customer & Account inside EazyBank")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status Created"),
			@ApiResponse(responseCode = "417", description = "Expectation Failed"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto cusotomerDto) {
		boolean isUpdated = iAccountsService.updateAccount(cusotomerDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_500));

		}
	}

	@Operation(summary = "Delete Account and Customer Degtails REST API", description = "REST API to create new customer & Account inside EazyBank")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error") })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must 10 digits") String mobileNumber) {
		boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.MESSAGE_417_DELETE, AccountsConstants.MESSAGE_500));

		}

	}

	@Operation(summary = "Get Build information", description = "Get Build information that is deployed into accounts microservice")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}

	@Operation(summary = "Get Java version", description = "Get Java verson details that is installed into accounts microservice")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/java-version")
	public ResponseEntity<String> getJavaVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
	}

	@Operation(summary = "Get Contact Info", description = "Contact Info details that can be reached out in case of any issues")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/contact-info")
	public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
	}

}
