package com.example.Reto1Server.utils.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "User does not exist")
public class UserNotFound extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UserNotFound(String message) {
		super(message);
	}
}
