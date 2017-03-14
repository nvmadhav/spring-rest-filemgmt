package com.documentmgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Global exception handler class
 * @author vnondapaka
 *
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)  
	@ExceptionHandler(value = IllegalArgumentException.class)  
	public String handleIllegalArgumentException(IllegalArgumentException e){  
		return e.getMessage();  
	}  

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)  
	public String handleException(Exception e){return e.getMessage();}  


}  

