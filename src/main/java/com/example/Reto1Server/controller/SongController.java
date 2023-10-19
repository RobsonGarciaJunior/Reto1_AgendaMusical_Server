package com.example.Reto1Server.controller;

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

import com.example.Reto1Server.model.Song;
import com.example.Reto1Server.repository.SongRepository;

@RestController
@RequestMapping("api")
public class SongController {
	@Autowired
	SongRepository songRepository;
	@GetMapping("/songs")
	public ResponseEntity<List<Song>> getSongs() {
		return new ResponseEntity<>(songRepository.findAll(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/songs/{id}")
	public ResponseEntity<Song> getSong(@PathVariable("id") long id) {
		return new ResponseEntity<>(songRepository.findbyId(id), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/songs")
	public ResponseEntity<?> createSong(@RequestBody Song song) {
		songRepository.createSong(song);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/songs/{id}")
	public ResponseEntity<?> updateSong(@PathVariable("id") long id, @RequestBody Song song){
		song.setIdSong((int)id);
		songRepository.updateSong(song);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/songs/{id}")
	public ResponseEntity<?> deleteSong(@PathVariable("id") long id) {
		songRepository.deleteSong(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}