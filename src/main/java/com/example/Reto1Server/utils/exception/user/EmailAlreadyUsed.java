package com.example.Reto1Server.utils.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Email Already Used")
public class EmailAlreadyUsed extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmailAlreadyUsed(String message) {
		super(message);
	}
}
