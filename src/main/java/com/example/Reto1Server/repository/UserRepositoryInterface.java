package com.example.Reto1Server.repository;

import com.example.Reto1Server.model.User;

public interface UserRepositoryInterface {

	User getById(Integer id);
	int createUser(User user);
	int updateUser(User user);
	int deleteUser(Integer id);
	int createFavorite(Integer userId, Integer songId);
	int deleteFavorite(Integer userId, Integer songId);
}
