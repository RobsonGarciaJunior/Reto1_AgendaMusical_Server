package com.example.Reto1Server.repository;

import java.util.List;

import com.example.Reto1Server.model.Song;

public interface SongRepositoryInterface {

	List<Song> findAll();
	Song findbyId(long id);
	int createSong(Song song);
	int updateSong(Song song);
	int deleteSong(Long id);
	List<Song> getAllFavorites(Integer userId);
}
