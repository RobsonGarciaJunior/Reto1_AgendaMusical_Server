package com.example.Reto1Server.security.repository;

import java.util.Optional;

import com.example.Reto1Server.security.model.UserDAO;


public interface AuthRepository {
	Optional<UserDAO> getUserByEmail(String email);
	int create(UserDAO userDAO);
	int updateUserPassword(UserDAO userDAO);
}
