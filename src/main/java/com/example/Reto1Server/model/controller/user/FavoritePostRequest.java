package com.example.Reto1Server.model.controller.user;

public class FavoritePostRequest {

	private Integer idSong;
	
	FavoritePostRequest(Integer idSong){
		this.idSong = idSong;
	}


	public Integer getIdSong() {
		return idSong;
	}

	public void setIdSong(Integer idSong) {
		this.idSong = idSong;
	}

	@Override
	public String toString() {
		return "FavoritePostRequest [ idSong=" + idSong + "]";
	}
	
	
}
