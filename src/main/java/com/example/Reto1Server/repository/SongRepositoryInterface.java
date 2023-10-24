package com.example.Reto1Server.repository;

import java.util.List;

import com.example.Reto1Server.model.repository.Song;

public interface SongRepositoryInterface {

	List<Song> getAllSong();
	Song getSongById(Integer id);
	int createSong(Song song);
	int updateSong(Song song);
	int deleteSong(Integer id);
	List<Song> getAllFavorites(Integer userId);
}
