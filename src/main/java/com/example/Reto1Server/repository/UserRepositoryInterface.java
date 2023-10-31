package com.example.Reto1Server.repository;

import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

public interface UserRepositoryInterface {

	UserDAO getUserById(Integer id) throws UserNotFound;
//	UserDAO getUserByEmailAndPassword(UserDAO user) throws UserNotFound;
//	int registerUser(UserDAO user) throws EmailAlreadyRegistered;
	int deleteUser(Integer id);
	int createFavorite(Integer idUser, Integer idSong);
	int deleteFavorite(Integer idUser, Integer idSong);
	
}
