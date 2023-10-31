package com.example.Reto1Server.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.security.repository.AuthRepository;


@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

	@Autowired
	AuthRepository authRepository;

	public int create(UserDTO userDTO) {
		UserDAO userDAO = convertFromDTOToDAO(userDTO);
		return authRepository.create(userDAO);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// esta es la funcion que busca al usuario por email. 
		// ya que en este caso el campo de login es el email
		// si fuese otro, realizar otra funcion
		return authRepository.getUserByEmail(username)
				.orElseThrow(
						() -> new UsernameNotFoundException("User " + username + " not found"));
	}

	@Override
	public int updateUserPassword(UserDTO userDTO) {
		UserDAO userDAO = convertFromDTOToDAO(userDTO);
		return authRepository.updateUserPassword(userDAO);
	}

	private UserDAO convertFromDTOToDAO(UserDTO userDTO) {

		UserDAO response = new UserDAO(
				userDTO.getIdUser(),
				userDTO.getName(),
				userDTO.getSurname(),
				userDTO.getEmail(),
				userDTO.getPassword()
				);
		return response;
	}
}
