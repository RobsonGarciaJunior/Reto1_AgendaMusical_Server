package com.example.Reto1Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Reto1Server.model.repository.User;
import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.repository.UserRepositoryInterface;

@Service
public class UserService implements IUserService{

	//TODO SE PUEDE CREAR UNA EXCEPCION PARA CUANDO SE REPITA EL EMAIL ANNADIDO
	//TODO SE PUEDE PONER UNA EXCEPCION PARA CUANDO EL USUARIO NO EXISTA
	
	@Autowired
	UserRepositoryInterface userRepository;

	@Autowired
	ISongService songService;

	@Override
	public UserDTO getUserById(Integer id) {

		User user = userRepository.getUserById(id);

		return convertFromDAOToDTO(user);
	}
	
	@Override
	public UserDTO getUserWithAllFavorites(Integer id) {
		
		UserDTO response = getUserById(id);
		response.setFavoriteList(getAllFavorites(id));

		return response;
	}

	@Override
	public int createUser(UserDTO userDTO) {

		User user = convertFromDTOToDAO(userDTO);

		return userRepository.createUser(user);
	}

	@Override
	public int updateUser(UserDTO userDTO) {

		User user = convertFromDTOToDAO(userDTO);

		
		return userRepository.updateUser(user);
	}

	@Override
	public int deleteUser(Integer id) {

		return userRepository.deleteUser(id);
	}

	@Override
	public List<SongDTO> getAllFavorites(Integer idUser) {

		return songService.getSongsByIdUser(idUser);
	}

	@Override
	public int createFavorite(Integer idUser, Integer idSong) {

		return userRepository.createFavorite(idUser, idSong);
	}

	@Override
	public int deleteFavorite(Integer idUser, Integer idSong) {

		return userRepository.deleteFavorite(idUser, idSong);
	}

	//CONVERTS
	//---------------------------------------
	private UserDTO convertFromDAOToDTO(User user) {

		UserDTO response = new UserDTO(
				user.getIdUser(),
				user.getName(),
				user.getSurname(),
				user.getEmail(),
				user.getPassword()
				);
		return response;
	}

	private User convertFromDTOToDAO(UserDTO userDTO) {

		User response = new User(
				userDTO.getIdUser(),
				userDTO.getName(),
				userDTO.getSurname(),
				userDTO.getEmail(),
				userDTO.getPassword()
				);
		return response;
	}
	//---------------------------------------


}
