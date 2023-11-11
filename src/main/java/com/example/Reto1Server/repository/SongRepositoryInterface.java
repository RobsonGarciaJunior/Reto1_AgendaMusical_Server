package com.example.Reto1Server.repository;

import java.util.List;

import com.example.Reto1Server.model.repository.Song;
import com.example.Reto1Server.utils.exception.song.SongNotFound;
import com.example.Reto1Server.utils.exception.song.UrlAlreadyExists;

public interface SongRepositoryInterface {

	List<Song> getAllSong();
	Song getSongById(Integer id) throws SongNotFound;
	int createSong(Song song) throws UrlAlreadyExists;
	int updateSong(Song song) throws UrlAlreadyExists;
	int deleteSong(Integer id);
	List<Song> getAllFavorites(Integer userId);
	List<Song> getSongByAuthor(String author) throws SongNotFound;

	
}
