package com.example.Reto1Server.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reto1Server.model.controller.song.SongGetResponse;
import com.example.Reto1Server.model.controller.user.UserGetResponse;
import com.example.Reto1Server.model.controller.user.UserPostRequest;
import com.example.Reto1Server.model.controller.user.UserPutRequest;
import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.service.IUserService;
import com.example.Reto1Server.utils.exception.user.EmailAlreadyRegistered;
import com.example.Reto1Server.utils.exception.user.UserNotFound;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	IUserService userService;

	@PostMapping("/users/login")
	public ResponseEntity<UserGetResponse> getUserByEmailAndPassword(@RequestBody UserPostRequest userPostRequest) throws UserNotFound {

		UserDTO userDTOToService = convertFromPostRequestToDTO(userPostRequest);
		
		UserDTO userDTOResponse = userService.getUserByEmailAndPassword(userDTOToService);
		
		UserGetResponse userGetResponse = convertFromUserDTOToUserGetResponse(userDTOResponse);

		return new ResponseEntity<>(userGetResponse, HttpStatus.ACCEPTED);
	}

	@PostMapping("/users/register")
	public ResponseEntity<?> registerUser(@RequestBody UserPostRequest userPostRequest) throws EmailAlreadyRegistered {

		UserDTO userDTO= convertFromPostRequestToDTO(userPostRequest);

		return new ResponseEntity<>(userService.registerUser(userDTO),HttpStatus.CREATED);
	}

	@PutMapping("users/{id}")
	public ResponseEntity<?> updateUserPassword(@PathVariable("id") Integer id, @RequestBody UserPutRequest userPutRequest) throws EmailAlreadyRegistered{

		UserDTO userDTO = convertFromPutRequestToDTO(userPutRequest);

		userDTO.setIdUser((int)id);

		return new ResponseEntity<>(userService.updateUserPassword(userDTO),HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {

		return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.NO_CONTENT);
	}

	@GetMapping("/users/{id}/favorites")
	public  ResponseEntity<UserGetResponse> getAllFavorites(@PathVariable("id") Integer id) throws UserNotFound{

		UserGetResponse userGetResponse = convertFromUserDTOToUserGetResponseWithFavoriteList(userService.getUserWithAllFavorites(id));

		return new ResponseEntity<>(userGetResponse, HttpStatus.ACCEPTED);
	}


	@PostMapping("/users/favorites")
	public ResponseEntity<?> createFavorite(@RequestBody ObjectNode objectNode) {

		Integer idUser = objectNode.get("idUser").asInt();
		Integer idSong = objectNode.get("idSong").asInt();

		return new ResponseEntity<>(userService.createFavorite(idUser, idSong),HttpStatus.CREATED);
	}

	@DeleteMapping("/users/{idUser}/favorites/{idSong}")
	public ResponseEntity<?> deleteFavorite(@PathVariable("idUser") Integer idUser, @PathVariable("idSong") Integer idSong) {

		return new ResponseEntity<>(userService.deleteFavorite(idUser, idSong),HttpStatus.NO_CONTENT);
	}


	//CONVERTS
	//---------------------------------------
	private UserGetResponse convertFromUserDTOToUserGetResponse(UserDTO userDTO) {

		UserGetResponse response = new UserGetResponse(
				userDTO.getIdUser(),
				userDTO.getName(),
				userDTO.getSurname(),
				userDTO.getEmail(),
				userDTO.getPassword()
				);

		return response;
	}

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

	private UserDTO convertFromPostRequestToDTO(UserPostRequest userPostRequest) {

		UserDTO response = new UserDTO(
				userPostRequest.getIdUser(),
				userPostRequest.getName(),
				userPostRequest.getSurname(),
				userPostRequest.getEmail(),
				userPostRequest.getPassword()
				);
		return response;
	}

	private UserDTO convertFromPutRequestToDTO(UserPutRequest userPutRequest) {

		UserDTO response = new UserDTO(
				userPutRequest.getIdUser(),
				userPutRequest.getName(),
				userPutRequest.getSurname(),
				userPutRequest.getEmail(),
				userPutRequest.getPassword()
				);
		return response;
	}
	//---------------------------------------
}	