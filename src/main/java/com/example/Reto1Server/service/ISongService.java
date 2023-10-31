package com.example.Reto1Server.service;

import java.util.List;

import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.utils.exception.song.SongNotFound;
import com.example.Reto1Server.utils.exception.song.UrlAlreadyExists;

public interface ISongService {
	
	List<SongDTO> getAllSongs();
	SongDTO getSongById(Integer id) throws SongNotFound;
	int createSong(SongDTO song) throws UrlAlreadyExists;
	int updateSong(SongDTO song) throws UrlAlreadyExists;
	int deleteSong(Integer id);
	List<SongDTO> getFavoriteSongsByIdUser(Integer idUser);
}
