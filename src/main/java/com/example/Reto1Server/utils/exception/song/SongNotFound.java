package com.example.Reto1Server.utils.exception.song;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "Song does not exist")
public class SongNotFound extends Exception{
	
	private static final long serialVersionUID = 1L;

	public SongNotFound(String message) {
		super(message);
	}
}
