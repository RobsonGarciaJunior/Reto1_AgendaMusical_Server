package com.example.Reto1Server.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Reto1Server.model.repository.Song;
import com.example.Reto1Server.utils.exception.song.SongNotFound;
import com.example.Reto1Server.utils.exception.song.UrlAlreadyExists;

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
	public Song getSongById(Integer id) throws SongNotFound {
		try {
			return jdbcTemplate.queryForObject("SELECT * from songs where idSong = ?", BeanPropertyRowMapper.newInstance(Song.class), id);
		}catch(EmptyResultDataAccessException erdae){
			throw new SongNotFound("Song does not exist");
		}

	}

	@Override
	public int createSong(Song song) throws UrlAlreadyExists{

		try {
			return jdbcTemplate.update("INSERT INTO songs (title, author, url) VALUES(?, ?, ?)", 
					new Object[] { song.getTitle(), song.getAuthor(), song.getUrl()});

		}catch(DataIntegrityViolationException edive) {
			throw new UrlAlreadyExists("URL Already Exists");
		}

	}

	@Override
	public int updateSong(Song song) throws UrlAlreadyExists {
		try {
			return jdbcTemplate.update("UPDATE songs SET title = ?, author = ?, url = ? WHERE idSong = ?", 
					new Object[] { song.getTitle(), song.getAuthor(), song.getUrl(), song.getIdSong()});
		}catch(DataIntegrityViolationException edive) {
			throw new UrlAlreadyExists("URL Already Exists");
		}

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
