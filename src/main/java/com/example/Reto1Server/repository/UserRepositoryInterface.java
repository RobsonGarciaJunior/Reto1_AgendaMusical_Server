package com.example.Reto1Server.repository;

import com.example.Reto1Server.model.repository.User;

public interface UserRepositoryInterface {

	User getUserById(Integer id);
	int createUser(User user);
	int updateUser(User user);
	int deleteUser(Integer id);
	int createFavorite(Integer idUser, Integer idSong);
	int deleteFavorite(Integer idUser, Integer idSong);
}
