package com.example.Reto1Server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Reto1Server.model.repository.Song;
import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.repository.SongRepositoryInterface;

@Service
public class SongService implements ISongService{

	//TODO SE PUEDE CREAR UNA EXCEPCION PARA CUANDO SE REPITA LA URL ANNADIDA
	//TODO SE PUEDE PONER UNA EXCEPCION PARA CUANDO LA CANCION NO EXISTA
	
	@Autowired
	SongRepositoryInterface songRepository;

	@Override
	public List<SongDTO> getAllSongs() {

		List<Song> listSong = songRepository.getAllSong();
		List <SongDTO> response = new ArrayList<SongDTO>();

		//Transform every DAO from the list to DTO
		for(Song song : listSong){
			response.add(convertFromDAOToDTO(song));
		}
		return response;
	}

	@Override
	public SongDTO getSongById(Integer id) {

		Song song = songRepository.getSongById(id);

		return convertFromDAOToDTO(song);
	}

	@Override
	public int createSong(SongDTO songDTO) {

		Song song = convertFromDTOToDAO(songDTO);

		return songRepository.createSong(song);
	}

	@Override
	public int updateSong(SongDTO songDTO) {

		Song song = convertFromDTOToDAO(songDTO);

		return songRepository.updateSong(song);
	}

	@Override
	public int deleteSong(Integer id) {

		return songRepository.deleteSong(id);
	}

	@Override
	public List<SongDTO> getSongsByIdUser(Integer idUser) {

		List<Song> listSong = songRepository.getAllFavorites(idUser);
		List <SongDTO> response = new ArrayList<SongDTO>();

		//Transform every DAO from the list to DTO
		for(Song song : listSong){
			response.add(convertFromDAOToDTO(song));
		}
		return response;
	}

	//CONVERTS
	//---------------------------------------
	private SongDTO convertFromDAOToDTO(Song song) {

		SongDTO response = new SongDTO(
				song.getIdSong(),
				song.getTitle(),
				song.getAuthor(),
				song.getUrl()
				);
		return response;
	}

	private Song convertFromDTOToDAO(SongDTO songDTO) {

		Song response = new Song(
				songDTO.getIdSong(),
				songDTO.getTitle(),
				songDTO.getAuthor(),
				songDTO.getUrl()
				);
		return response;
	}
	//---------------------------------------

}
