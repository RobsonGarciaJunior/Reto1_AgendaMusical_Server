package com.example.Reto1Server.repository;

import com.example.Reto1Server.model.repository.User;
import com.example.Reto1Server.utils.exception.user.EmailAlreadyRegistered;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

public interface UserRepositoryInterface {

	User getUserById(Integer id) throws UserNotFound;
	User getUserByEmailAndPassword(User user) throws UserNotFound;
	int registerUser(User user) throws EmailAlreadyRegistered;
	int updateUserPassword(User user) throws EmailAlreadyRegistered;
	int deleteUser(Integer id);
	int createFavorite(Integer idUser, Integer idSong);
	int deleteFavorite(Integer idUser, Integer idSong);
	
}
