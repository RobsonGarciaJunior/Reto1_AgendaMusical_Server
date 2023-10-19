package com.example.Reto1Server.controller;


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

import com.example.Reto1Server.model.Song;
import com.example.Reto1Server.model.User;
import com.example.Reto1Server.repository.UserRepository;

@RestController
@RequestMapping("api")
public class UserController {
	@Autowired
	UserRepository userRepository;
	@GetMapping("/users{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(userRepository.getById(id), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody User user){
		user.setIdUser((int)id);
		userRepository.updateUser(user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		userRepository.createUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
		userRepository.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//TODO REALIZAR EL METODO CUANDO ESTE TERMINADO EL APARTADO DE SERVICE
	//@GetMapping("/users/{id}/favorites")
	
	@PostMapping("/users/favorites")
	public ResponseEntity<?> createFavorite(@RequestBody Integer idUser, Integer idSong) {
		userRepository.createFavorite(idUser, idSong);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{idUser}/favorites/{idSong}")
	public ResponseEntity<?> deleteFavorite(@PathVariable("id") Integer idUser, Integer idSong) {
		userRepository.deleteFavorite(idUser, idSong);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}	
