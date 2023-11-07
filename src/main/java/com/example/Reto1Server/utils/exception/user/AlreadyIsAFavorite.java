package com.example.Reto1Server.utils.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Cannot add to favorite because is already a favorite")
public class AlreadyIsAFavorite extends Exception{
	
	private static final long serialVersionUID = 1L;

	public AlreadyIsAFavorite(String message) {
		super(message);
	}
}
