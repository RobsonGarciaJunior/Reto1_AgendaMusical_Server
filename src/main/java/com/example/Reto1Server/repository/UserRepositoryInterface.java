package com.example.Reto1Server.repository;

import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.utils.exception.user.AlreadyIsAFavorite;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

public interface UserRepositoryInterface {

	UserDAO getUserById(Integer id) throws UserNotFound;
	int createFavorite(Integer idUser, Integer idSong) throws AlreadyIsAFavorite;
	int deleteFavorite(Integer idUser, Integer idSong);
	
}
