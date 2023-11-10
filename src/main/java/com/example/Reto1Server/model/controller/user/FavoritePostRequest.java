package com.example.Reto1Server.model.controller.user;

public class FavoritePostRequest {

	private int idSong;
	
	FavoritePostRequest(){}


	public int getIdSong() {
		return idSong;
	}

	public void setIdSong(int idSong) {
		this.idSong = idSong;
	}

	@Override
	public String toString() {
		return "FavoritePostRequest [ idSong=" + idSong + "]";
	}
	
	
}
