package com.qa.gamestore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.IM_USED, reason = "That username is already in use")
public class UsernameAlreadyExistsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "That username is already in use";
	}
	
}
