package com.example.Reto1Server.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.security.repository.AuthRepository;
import com.example.Reto1Server.utils.exception.user.EmailAlreadyUsed;
import com.example.Reto1Server.utils.exception.user.WrongPasswordIntroduced;


@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

	@Autowired
	AuthRepository authRepository;

	public int create(UserDTO userDTO) throws EmailAlreadyUsed {
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
	public int updateUserPassword(UserDTO userDTO, String oldPassword) throws WrongPasswordIntroduced {
	    UserDAO userDAO = convertFromDTOToDAO(userDTO);
	    // Obtén la contraseña actual de la BBDD
	    String actualDBPassword = authRepository.getActualDBPassword(userDAO.getIdUser());

	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    // Comparamos la contraseña de la BBDD con la nueva que ha introducido el usuario
	    if (passwordEncoder.matches(oldPassword, actualDBPassword)) {
	        // Actualizamos la contraseña
	        return authRepository.updateUserPassword(userDAO);
	    }
	    // Lanzamos el error de que la contraseña no coincide
	    throw new WrongPasswordIntroduced("La contraseña antigua no coincide");
	}

	@Override
	public int deleteUser(Integer idUser) {
		// TODO Auto-generated method stub
		return authRepository.deleteUser(idUser);
	}

	//CONVERTS
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