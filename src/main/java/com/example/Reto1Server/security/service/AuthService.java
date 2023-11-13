package com.example.Reto1Server.security.service;

import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.utils.exception.user.EmailAlreadyUsed;


public interface AuthService {
	int create(UserDTO user) throws EmailAlreadyUsed;
	int updateUserPassword(UserDTO user, String oldPassword);
	int deleteUser(Integer idUser);
}
