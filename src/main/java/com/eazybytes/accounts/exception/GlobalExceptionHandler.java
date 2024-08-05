package com.eazybytes.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.eazybytes.accounts.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * this checks all other runtime exceptions
	 * @param exception
	 * @param webRequeste
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto>handleGlobalException(Exception exception,
	 WebRequest webRequeste){
		
		ErrorResponseDto errorResposneDTO = new ErrorResponseDto(
				webRequeste.getDescription(false), 
				HttpStatus.INTERNAL_SERVER_ERROR, 
				exception.getMessage(), 
				LocalDateTime.now()
				);
		
		return new ResponseEntity<ErrorResponseDto>(errorResposneDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto>handleCustomerAlredyExistException(CustomerAlreadyExistsException exception,
	 WebRequest webRequeste){
		
		ErrorResponseDto errorResposneDTO = new ErrorResponseDto(
				webRequeste.getDescription(false), 
				HttpStatus.BAD_REQUEST, 
				exception.getMessage(), 
				LocalDateTime.now()
				);
		
		return new ResponseEntity<ErrorResponseDto>(errorResposneDTO, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto>handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequeste){
		ErrorResponseDto errorResposneDTO = new ErrorResponseDto(
				webRequeste.getDescription(true), 
				HttpStatus.NOT_FOUND, 
				exception.getMessage(), 
				LocalDateTime.now()
				);
		
		return new ResponseEntity<ErrorResponseDto>(errorResposneDTO, HttpStatus.NOT_FOUND);
	}
}
