package com.example.Reto1Server.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Reto1Server.model.repository.Song;
@Repository
public class SongRepository implements SongRepositoryInterface{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Song> getAllSong() {
		try {
			return jdbcTemplate.query("SELECT * from songs",
					BeanPropertyRowMapper.newInstance(Song.class)
					);
		} catch(IncorrectResultSizeDataAccessException e) {
		return null;
		}
	}
	@Override
	public Song getSongById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * from songs where idSong = ?", BeanPropertyRowMapper.newInstance(Song.class), id);
	}

	@Override
	public int createSong(Song song) {
		return jdbcTemplate.update("INSERT INTO songs (title, author, url) VALUES(?, ?, ?)", 
				new Object[] { song.getTitle(), song.getAuthor(), song.getUrl()});
	}

	@Override
	public int updateSong(Song song) {
		return jdbcTemplate.update("UPDATE songs SET title = ?, author = ?, url = ? WHERE idSong = ?", 
				new Object[] { song.getTitle(), song.getAuthor(), song.getUrl(), song.getIdSong()});
	}

	@Override
	public int deleteSong(Integer id) {
		return jdbcTemplate.update("DELETE FROM songs WHERE idSong = ?", id);
	}
	
	@Override
	public List<Song> getAllFavorites(Integer idUser) {
		try {
			return jdbcTemplate.query("SELECT f.idSong, s.title, s.author, s.url  \r\n"
					+ "from favorites f join songs s on f.idSong = s.idSong where f.idUser = ?",
					BeanPropertyRowMapper.newInstance(Song.class), idUser);
		} catch(IncorrectResultSizeDataAccessException e) {
		return null;
		}
	}

}
