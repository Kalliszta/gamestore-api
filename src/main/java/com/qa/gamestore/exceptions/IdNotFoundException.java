package com.qa.gamestore.exceptions;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Id not found")
public class IdNotFoundException extends NoSuchElementException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
