package com.example.Reto1Server.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Reto1Server.model.repository.User;
import com.example.Reto1Server.utils.exception.user.EmailAlreadyRegistered;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

@Repository
public class UserRepository implements UserRepositoryInterface{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User getUserByEmailAndPassword(User user) throws UserNotFound {
		try {
			//TODO VER PQ NO DEVUELVE EL USUARIO
			return jdbcTemplate.queryForObject("SELECT * from users WHERE email = ? and password = ?", BeanPropertyRowMapper.newInstance(User.class), user.getEmail(), user.getPassword());	
		}catch(EmptyResultDataAccessException erdae){
			throw new UserNotFound("User does not exist");
		}
	}

	@Override
	public User getUserById(Integer id) throws UserNotFound {
		try {
			return jdbcTemplate.queryForObject("SELECT * from users where idUser = ?", BeanPropertyRowMapper.newInstance(User.class), id);	
		}catch(EmptyResultDataAccessException erdae){
			throw new UserNotFound("User does not exist");
		}
	}

	@Override
	public int registerUser(User user) throws EmailAlreadyRegistered {
		try {
			return jdbcTemplate.update("INSERT INTO users (name, surname, email, password) VALUES(?, ?, ?, ?)", 
					new Object[] { user.getName(), user.getSurname(), user.getEmail(), user.getPassword()});

		}catch(DataIntegrityViolationException edive) {
			throw new EmailAlreadyRegistered("Email Already Registered");
		}

	}
	
	@Override
	public int updateUserPassword(User user) throws EmailAlreadyRegistered {
		try{
			return jdbcTemplate.update("UPDATE users SET password = ? WHERE idUser = ?", 
					new Object[] { user.getPassword(), user.getIdUser()});

		}catch(DataIntegrityViolationException edive) {
			throw new EmailAlreadyRegistered("Email Already Registered");
		}
	}

	@Override
	public int deleteUser(Integer id) {
		return jdbcTemplate.update("DELETE FROM users WHERE idUser = ?", id);
	}

	@Override
	public int createFavorite(Integer idUser, Integer idSong) {
		return jdbcTemplate.update("INSERT INTO favorites (idUser, idSong) VALUES(?, ?)", idUser, idSong);
	}

	@Override
	public int deleteFavorite(Integer idUser, Integer idSong) {
		return jdbcTemplate.update("DELETE FROM favorites WHERE idUser = ? and idSong = ?", idUser, idSong);
	}


}
