package com.example.Reto1Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.repository.UserRepositoryInterface;
import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.utils.exception.user.UserNotFound;

@Service
public class UserService implements IUserService{

	@Autowired
	UserRepositoryInterface userRepository;

	@Autowired
	ISongService songService;

	@Override
	public UserDTO getUserById(Integer id) throws UserNotFound {

		UserDAO userDAO = userRepository.getUserById(id);

		return convertFromDAOToDTO(userDAO);
	}

	//
	//	@Override
	//	public UserDTO getUserByEmailAndPassword(UserDTO userDTO) throws UserNotFound {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	//
	//
	//	@Override
	//	public int registerUser(UserDTO userDTO) throws EmailAlreadyRegistered {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}

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
	private UserDTO convertFromDAOToDTO(UserDAO userDAO) {

		UserDTO response = new UserDTO(
				userDAO.getIdUser(),
				userDAO.getName(),
				userDAO.getSurname(),
				userDAO.getEmail(),
				userDAO.getPassword()
				);
		return response;
	}

//	private UserDAO convertFromDTOToDAO(UserDTO userDTO) {
//
//		UserDAO response = new UserDAO(
//				userDTO.getIdUser(),
//				userDTO.getName(),
//				userDTO.getSurname(),
//				userDTO.getEmail(),
//				userDTO.getPassword()
//				);
//		return response;
//	}
	//---------------------------------------





}
