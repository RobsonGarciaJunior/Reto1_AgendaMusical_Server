package com.example.Reto1Server.service;

import java.util.List;

import com.example.Reto1Server.model.service.SongDTO;

public interface ISongService {
	
	List<SongDTO> getAllSongs();
	SongDTO getSongById(Integer id);
	int createSong(SongDTO song);
	int updateSong(SongDTO song);
	int deleteSong(Integer id);
	List<SongDTO> getSongsByIdUser(Integer idUser);
}
