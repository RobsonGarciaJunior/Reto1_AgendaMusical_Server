package com.example.Reto1Server.service;


import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

public interface IUserService {

	UserDTO getUserById(Integer id) throws UserNotFound;
//	UserDTO getUserByEmailAndPassword(UserDTO userDTO) throws UserNotFound;
//	int registerUser(UserDTO userDTO) throws EmailAlreadyRegistered;
//	int deleteUser(Integer id);
	int createFavorite (Integer idUser, Integer idSong);
	int deleteFavorite (Integer idUser, Integer idSong);
	UserDTO getUserWithAllFavorites(Integer id) throws UserNotFound;
}
