package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

/**
 * @author abshaikh
 *
 */
public interface IAccountsService {
	
	void createAccount(CustomerDto customerDto);
}
