package com.example.Reto1Server.utils.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "Password Does not match")
public class WrongPasswordIntroduced extends Exception{
	
	private static final long serialVersionUID = 1L;

	public WrongPasswordIntroduced(String message) {
		super(message);
	}
}
