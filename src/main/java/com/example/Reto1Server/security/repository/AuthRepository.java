package com.example.Reto1Server.security.repository;

import java.util.Optional;

import com.example.Reto1Server.security.model.UserDAO;


public interface AuthRepository {
	Optional<UserDAO> getUserByEmail(String email);
	int create(UserDAO userDAO);
	String getActualDBPassword(Integer idUser);
	int updateUserPassword(UserDAO user);
	int deleteUser(Integer idUser);
}
