package com.example.Reto1Server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Reto1Server.model.repository.User;

@Repository
public class UserRepository implements UserRepositoryInterface{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User getUserById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * from users where idUser = ?", BeanPropertyRowMapper.newInstance(User.class), id);
	}

	@Override
	public int createUser(User user) {
		return jdbcTemplate.update("INSERT INTO users (name, surname, email, password) VALUES(?, ?, ?, ?)", 
				new Object[] { user.getName(), user.getSurname(), user.getEmail(), user.getPassword()});
	}

	@Override
	public int updateUser(User user) {
		return jdbcTemplate.update("UPDATE users SET name = ?, surname = ?, email = ?, password = ? WHERE idUser = ?", 
				new Object[] { user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getIdUser()});
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
