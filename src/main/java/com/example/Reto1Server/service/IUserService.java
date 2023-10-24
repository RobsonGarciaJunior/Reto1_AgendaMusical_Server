package com.example.Reto1Server.service;

import java.util.List;

import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.model.service.UserDTO;

public interface IUserService {

	UserDTO getUserById(Integer id);
	int createUser(UserDTO userDTO);
	int updateUser(UserDTO userDTO);
	int deleteUser(Integer id);
	List<SongDTO> getAllFavorites(Integer idUser);
	int createFavorite (Integer idUser, Integer idSong);
	int deleteFavorite (Integer idUser, Integer idSong);
	UserDTO getUserWithAllFavorites(Integer id);
}
