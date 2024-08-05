package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

/**
 * @author abshaikh
 *
 */
public interface IAccountsService {

	/**
	 * @param customerDto
	 */
	void createAccount(CustomerDto customerDto);

	/**
	 * @param mobileNumber
	 * @return
	 */
	CustomerDto fetchAccount(String mobileNumber);

	/**
	 * @param customerDto
	 * @return
	 */
	boolean updateAccount(CustomerDto customerDto);

	/**
	 * 
	 * @param mobileNumber
	 * @return boolean indicating if the delete of the Account details is
	 *         successfull or not
	 */
	boolean deleteAccount(String mobileNumber);

}
