package com.example.Reto1Server.service;


import java.util.List;

import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.utils.exception.user.AlreadyIsAFavorite;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

public interface IUserService {
	
	List<SongDTO> getAllFavorites(Integer id) throws UserNotFound;
	int createFavorite (Integer idUser, Integer idSong) throws AlreadyIsAFavorite;
	int deleteFavorite (Integer idUser, Integer idSong);
	
}
