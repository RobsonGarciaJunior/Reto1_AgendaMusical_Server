package com.example.Reto1Server.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.utils.exception.user.AlreadyIsAFavorite;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

@Repository
public class UserRepository implements UserRepositoryInterface{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDAO getUserById(Integer id) throws UserNotFound {
		try {
			return jdbcTemplate.queryForObject("SELECT * from users where idUser = ?", BeanPropertyRowMapper.newInstance(UserDAO.class), id);	
		}catch(EmptyResultDataAccessException erdae){
			throw new UserNotFound("User does not exist");
		}
	}

	@Override
	public int createFavorite(Integer idUser, Integer idSong) throws AlreadyIsAFavorite {
		try {
			return jdbcTemplate.update("INSERT INTO favorites (idUser, idSong) VALUES(?, ?)", idUser, idSong);
		}catch(DataIntegrityViolationException icve) {
			throw new AlreadyIsAFavorite("Already exists as favorite");
		}
	}

	@Override
	public int deleteFavorite(Integer idUser, Integer idSong) {
		return jdbcTemplate.update("DELETE FROM favorites WHERE idUser = ? and idSong = ?", idUser, idSong);
	}


}
