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
import com.example.Reto1Server.model.controller.user.UserGetResponse;
import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.service.IUserService;
import com.example.Reto1Server.utils.exception.user.UserNotFound;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	IUserService userService;

//	// utilizamos el /me por que vamos a coger el nuestro, el que estamos logueado...
//	@GetMapping("/users/me")
//	public ResponseEntity<?> getUserInfo(Authentication authentication) {
//		// aqui podemos castearlo a UserDetails o User. El UserDetails es una interfaz, 
//		// si lo casteamos a la interfaz no podremos sacar campos como la ID del usuario
//		UserDAO userDetails = (UserDAO) authentication.getPrincipal();
//		UserGetResponse userGetResponse = convertFromDAOToGetResponse(userDetails);
//		// IMPORTANTE: por lo tanto, la ID del usuario no tiene que ir como parametro en la peticion del usuario
//
//		// aqui podriamos devolver datos del usuario. quizá no sea lo que queremos devolver o no lo querramos devolver
//		// es un ejemplo por que con userDetails.getId() tendríamos la ID del usuario sin que la pase por parametro
//		// necesario en algunos servicios: si quiero devolver una lista o elemento privado del usuario, no voy a querer
//		// que el usuario mande su ID por parametro. Ya que es trampeable.
//		// de ahi que sea "/me" en el ejemplo 
//
//		return ResponseEntity.ok().body(userDetails);
//	}

	@GetMapping("/users/me/favorites")
	public  ResponseEntity<UserGetResponse> getAllFavorites(Authentication authentication) throws UserNotFound{

		UserDAO userDetails = (UserDAO) authentication.getPrincipal();

		UserGetResponse userGetResponse = convertFromUserDTOToUserGetResponseWithFavoriteList(userService.getUserWithAllFavorites(userDetails.getIdUser()));

		return new ResponseEntity<>(userGetResponse, HttpStatus.ACCEPTED);
	}

	@PostMapping("/users/favorites")
	public ResponseEntity<?> createFavorite(Authentication authentication, @RequestBody ObjectNode objectNode) {

		UserDAO userDetails = (UserDAO) authentication.getPrincipal();
		Integer idSong = objectNode.get("idSong").asInt();

		return new ResponseEntity<>(userService.createFavorite(userDetails.getIdUser(), idSong),HttpStatus.CREATED);
	}

	@DeleteMapping("/users/me/favorites/{idSong}")
	public ResponseEntity<?> deleteFavorite(Authentication authentication, @PathVariable("idSong") Integer idSong) {
		UserDAO userDetails = (UserDAO) authentication.getPrincipal();
		return new ResponseEntity<>(userService.deleteFavorite(userDetails.getIdUser(), idSong),HttpStatus.NO_CONTENT);
	}


	//CONVERTS
	//---------------------------------------
	//	private UserGetResponse convertFromUserDTOToUserGetResponse(UserDTO userDTO) {
	//
	//		UserGetResponse response = new UserGetResponse(
	//				userDTO.getIdUser(),
	//				userDTO.getName(),
	//				userDTO.getSurname(),
	//				userDTO.getEmail(),
	//				userDTO.getPassword()
	//				);
	//
	//		return response;
	//	}

//
//	private UserGetResponse convertFromDAOToGetResponse(UserDAO userDetails) {
//		UserGetResponse response = new UserGetResponse(
//				userDetails.getIdUser(),
//				userDetails.getName(),
//				userDetails.getSurname(),
//				userDetails.getEmail(),
//				userDetails.getPassword()
//				);
//		return response;
//	}

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
