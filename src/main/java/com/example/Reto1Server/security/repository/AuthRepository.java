package com.example.Reto1Server.security.repository;

import java.util.Optional;

import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.utils.exception.user.EmailAlreadyUsed;


public interface AuthRepository {
	Optional<UserDAO> getUserByEmail(String email);
	int create(UserDAO userDAO) throws EmailAlreadyUsed;
	String getActualDBPassword(Integer idUser);
	int updateUserPassword(UserDAO user);
	int deleteUser(Integer idUser);
}
