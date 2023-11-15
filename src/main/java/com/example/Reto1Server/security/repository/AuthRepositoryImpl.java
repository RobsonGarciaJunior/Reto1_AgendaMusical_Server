package com.example.Reto1Server.security.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.utils.exception.user.EmailAlreadyUsed;

@Repository
public class AuthRepositoryImpl implements AuthRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Optional<UserDAO> getUserByEmail(String email) {
		// veremos el optional mas adelante
		try {
			UserDAO user = jdbcTemplate.queryForObject("SELECT * from users where email = ?", BeanPropertyRowMapper.newInstance(UserDAO.class), email);
			return Optional.of(user);
		} catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public int create(UserDAO user) throws EmailAlreadyUsed {
		// IMPORTANTE: la contrasenia ha tenido que ser cifrada antes de entrar aqui

		// TODO podria darnos excepcion por que el email es unico	
		try {
			return jdbcTemplate.update("INSERT INTO users (name, surname, email, password) VALUES(?, ?, ?, ?)",
					new Object[] { 
							user.getName(), user.getSurname(), user.getEmail(), user.getPassword() // debe estar cifrada de antemano
			}	
					);

		} catch (DataIntegrityViolationException e) {
			throw new EmailAlreadyUsed("Email Already Used");
		}

	}


	public String getActualDBPassword(Integer idUser){
		//No hago un try de la query porque se que el token esta bien
		return (String)jdbcTemplate.queryForObject("SELECT users.password FROM users WHERE idUser = ?", String.class, idUser);

	}

	@Override
	public int updateUserPassword(UserDAO user){
		return jdbcTemplate.update("UPDATE users SET password = ? WHERE idUser = ?", 
				new Object[] { user.getPassword(), user.getIdUser()});

	}

	@Override
	public int deleteUser(Integer idUser) {
		return jdbcTemplate.update("DELETE FROM users WHERE idUser = ?", idUser);
	}

}
