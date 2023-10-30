package com.example.Reto1Server.utils.exception.song;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "URL Already Exists")
public class UrlAlreadyExists extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UrlAlreadyExists(String message) {
		super(message);
	}
}
