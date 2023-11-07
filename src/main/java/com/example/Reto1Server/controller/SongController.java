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
import com.example.Reto1Server.model.controller.song.SongPostRequest;
import com.example.Reto1Server.model.controller.song.SongPutRequest;
import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.service.ISongService;
import com.example.Reto1Server.utils.exception.song.SongNotFound;
import com.example.Reto1Server.utils.exception.song.UrlAlreadyExists;


@RestController
@RequestMapping("api/songs")
public class SongController {

	@Autowired
	ISongService songService;

	@GetMapping
	public ResponseEntity<List<SongGetResponse>> getAllSongs() {

		List<SongDTO> listSongDTO = songService.getAllSongs();
		List<SongGetResponse> response = new ArrayList<SongGetResponse>(); 

		//Transform every DTO from the list to GetResponse
		for(SongDTO songDTO: listSongDTO) {
			response.add(convertFromSongDTOToSongGetResponse(songDTO));
		}
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SongGetResponse> getSongById(@PathVariable("id") Integer id) throws SongNotFound{

		SongDTO songDTO = songService.getSongById(id);
		SongGetResponse songGetResponse = convertFromSongDTOToSongGetResponse(songDTO);

		return new ResponseEntity<>(songGetResponse, HttpStatus.ACCEPTED);
	}

	@PostMapping
	public ResponseEntity<?> createSong(@RequestBody SongPostRequest songPostRequest) throws UrlAlreadyExists {

		SongDTO songDTO = convertFromPostRequestToDTO(songPostRequest);


		return new ResponseEntity<>(songService.createSong(songDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSong(@PathVariable("id") Integer id, @RequestBody SongPutRequest songPutRequest) throws UrlAlreadyExists{

		SongDTO songDTO = convertFromPutRequestToDTO(songPutRequest);

		songDTO.setIdSong(id);

		return new ResponseEntity<>(songService.updateSong(songDTO), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSong(@PathVariable("id") Integer id) {

		return new ResponseEntity<>(songService.deleteSong(id), HttpStatus.NO_CONTENT);
	}

	//CONVERTS
	//---------------------------------------
	private SongGetResponse convertFromSongDTOToSongGetResponse(SongDTO songDTO) {

		SongGetResponse response = new SongGetResponse(
				songDTO.getIdSong(),
				songDTO.getTitle(),
				songDTO.getAuthor(),
				songDTO.getUrl()
				);

		return response;
	}

	private SongDTO convertFromPostRequestToDTO(SongPostRequest songPostRequest) {

		SongDTO response = new SongDTO(
				songPostRequest.getIdSong(),
				songPostRequest.getTitle(),
				songPostRequest.getAuthor(),
				songPostRequest.getUrl()
				);
		return response;
	}

	private SongDTO convertFromPutRequestToDTO(SongPutRequest songPutRequest) {

		SongDTO response = new SongDTO(
				songPutRequest.getIdSong(),
				songPutRequest.getTitle(),
				songPutRequest.getAuthor(),
				songPutRequest.getUrl()
				);
		return response;
	}
	//---------------------------------------
}