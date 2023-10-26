package com.example.Reto1Server.utils.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Email Already Registered")
public class EmailAlreadyRegistered extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmailAlreadyRegistered(String message) {
		super(message);
	}
}
