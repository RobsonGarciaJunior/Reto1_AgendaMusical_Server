package com.example.Reto1Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Reto1Server.model.repository.User;
import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.repository.UserRepositoryInterface;
import com.example.Reto1Server.utils.exception.user.EmailAlreadyRegistered;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

@Service
public class UserService implements IUserService{

	@Autowired
	UserRepositoryInterface userRepository;

	@Autowired
	ISongService songService;

	@Override
	public UserDTO getUserByEmailAndPassword(UserDTO userDTO) throws UserNotFound {

		User userToRepository = convertFromDTOToDAO(userDTO);

		User userResponse = userRepository.getUserByEmailAndPassword(userToRepository);

		return convertFromDAOToDTO(userResponse);
	}

	@Override
	public UserDTO getUserById(Integer id) throws UserNotFound {

		User user = userRepository.getUserById(id);

		return convertFromDAOToDTO(user);
	}


	@Override
	public int registerUser(UserDTO userDTO) throws EmailAlreadyRegistered {

		User user = convertFromDTOToDAO(userDTO);

		return userRepository.registerUser(user);
	}

	@Override
	public int updateUserPassword(UserDTO userDTO) throws EmailAlreadyRegistered {

		User user = convertFromDTOToDAO(userDTO);


		return userRepository.updateUserPassword(user);
	}

	@Override
	public int deleteUser(Integer id) {

		return userRepository.deleteUser(id);
	}

	@Override
	public UserDTO getUserWithAllFavorites(Integer id) throws UserNotFound {

		UserDTO response = getUserById(id);
		List<SongDTO> allFavoriteSongs = songService.getFavoriteSongsByIdUser(id); 
		response.setFavoriteList(allFavoriteSongs);

		return response;
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
