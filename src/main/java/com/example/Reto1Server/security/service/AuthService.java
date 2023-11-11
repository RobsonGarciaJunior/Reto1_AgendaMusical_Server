package com.example.Reto1Server.security.service;

import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.utils.exception.user.WrongPasswordIntroduced;


public interface AuthService {
	int create(UserDTO user);
	int updateUserPassword(UserDTO user, String oldPassword) throws WrongPasswordIntroduced;
	int deleteUser(Integer idUser);
}
