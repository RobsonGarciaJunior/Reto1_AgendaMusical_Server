package com.example.Reto1Server.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reto1Server.model.controller.song.SongGetResponse;
import com.example.Reto1Server.model.controller.user.FavoritePostRequest;
import com.example.Reto1Server.model.controller.user.UserGetResponse;
import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.service.IUserService;
import com.example.Reto1Server.utils.exception.user.AlreadyIsAFavorite;
import com.example.Reto1Server.utils.exception.user.UserNotFound;


@RestController
@RequestMapping("api")
public class UserController {

	//TODO PREGUNTAR SI ES PROBLEMA AL BORRAR UNA FAVORITA SI LO PASO POR PARAMETRO
	@Autowired
	IUserService userService;

	@GetMapping("/users/me/favorites")
	public  ResponseEntity<UserGetResponse> getAllFavorites(Authentication authentication) throws UserNotFound{

		UserDAO userDetails = (UserDAO) authentication.getPrincipal();

		UserGetResponse userGetResponse = convertFromUserDTOToUserGetResponseWithFavoriteList(userService.getUserWithAllFavorites(userDetails.getIdUser()));

		return new ResponseEntity<>(userGetResponse, HttpStatus.ACCEPTED);
	}

	@PostMapping("/users/favorites")
	public ResponseEntity<?> createFavorite(Authentication authentication, @RequestBody FavoritePostRequest favoritePostRequest) throws AlreadyIsAFavorite{

		UserDAO userDetails = (UserDAO) authentication.getPrincipal();
		Integer idSong = favoritePostRequest.getIdSong();

		return new ResponseEntity<>(userService.createFavorite(userDetails.getIdUser(), idSong),HttpStatus.CREATED);
	}

	@DeleteMapping("/users/me/favorites/{idSong}")
	public ResponseEntity<?> deleteFavorite(Authentication authentication, @PathVariable("idSong") Integer idSong) {
		UserDAO userDetails = (UserDAO) authentication.getPrincipal();
		return new ResponseEntity<>(userService.deleteFavorite(userDetails.getIdUser(), idSong),HttpStatus.NO_CONTENT);
	}


	//CONVERTS


	private UserGetResponse convertFromUserDTOToUserGetResponseWithFavoriteList(UserDTO userDTO) {

		List<SongGetResponse> listOfFavourites = new ArrayList<SongGetResponse>();

		for(SongDTO songDTO: userDTO.getFavoriteList()) {
			listOfFavourites.add(convertFromSongDTOToSongGetResponse(songDTO));
		}

		UserGetResponse response = new UserGetResponse(
				userDTO.getIdUser(),
				userDTO.getName(),
				userDTO.getSurname(),
				userDTO.getEmail(),
				userDTO.getPassword()
				);

		response.setFavoriteList(listOfFavourites);
		return response;
	}

	private SongGetResponse convertFromSongDTOToSongGetResponse(SongDTO songDTO) {

		SongGetResponse response = new SongGetResponse(
				songDTO.getIdSong(),
				songDTO.getTitle(),
				songDTO.getAuthor(),
				songDTO.getUrl()
				);

		return response;
	}

	//	private UserDTO convertFromPostRequestToDTO(UserPostRequest userPostRequest) {
	//
	//		UserDTO response = new UserDTO(
	//				userPostRequest.getIdUser(),
	//				userPostRequest.getName(),
	//				userPostRequest.getSurname(),
	//				userPostRequest.getEmail(),
	//				userPostRequest.getPassword()
	//				);
	//		return response;
	//	}
	//
	//	private UserDTO convertFromPutRequestToDTO(UserPutRequest userPutRequest) {
	//
	//		UserDTO response = new UserDTO(
	//				userPutRequest.getIdUser(),
	//				userPutRequest.getName(),
	//				userPutRequest.getSurname(),
	//				userPutRequest.getEmail(),
	//				userPutRequest.getPassword()
	//				);
	//		return response;
	//	}
	//---------------------------------------
}	
