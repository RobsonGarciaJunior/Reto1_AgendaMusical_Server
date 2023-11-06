package com.example.Reto1Server.security.service;

import com.example.Reto1Server.model.service.UserDTO;


public interface AuthService {
	int create(UserDTO user);
	int updateUserPassword(UserDTO user, String oldPassword);
	int deleteUser(Integer idUser);
}
