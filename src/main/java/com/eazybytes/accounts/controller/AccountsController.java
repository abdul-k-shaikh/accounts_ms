package com.eazybytes.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
public class AccountsController {

//	@Autowired  here @AllArgsConstructor is helping , we comment this it wills how 500
	private IAccountsService iAccountsService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		iAccountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED) // line 23 at header level status created
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));

	}

	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam 
			@Pattern(regexp="(^$|[0-9]{10})", message="mobile number must 10 digits")
			String mobileNumber) {
		CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);

	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto>updateAccountDetails(@Valid @RequestBody CustomerDto cusotomerDto){
		boolean isUpdated = iAccountsService.updateAccount(cusotomerDto);
		if(isUpdated) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}else {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));

		}	
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam 
			@Pattern(regexp="(^$|[0-9]{10})", message="mobile number must 10 digits")
			String mobileNumber) {
		boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));

		}

	}

}
