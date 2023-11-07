package com.example.Reto1Server.service;


import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.utils.exception.user.AlreadyIsAFavorite;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

public interface IUserService {
	
	UserDTO getUserWithAllFavorites(Integer id) throws UserNotFound;
	UserDTO getUserById(Integer id) throws UserNotFound;
	int createFavorite (Integer idUser, Integer idSong) throws AlreadyIsAFavorite;
	int deleteFavorite (Integer idUser, Integer idSong);
	
}
