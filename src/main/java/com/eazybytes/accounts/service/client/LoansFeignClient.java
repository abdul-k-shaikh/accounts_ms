package com.eazybytes.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eazybytes.accounts.dto.CardsDto;
import com.eazybytes.accounts.dto.LoansDto;

@FeignClient("loans")
public interface LoansFeignClient {
	
	@GetMapping(value="/api/fetch", consumes="applications/json")
	public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);
}
