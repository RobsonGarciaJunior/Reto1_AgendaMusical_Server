package com.example.Reto1Server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Reto1Server.model.repository.Song;
import com.example.Reto1Server.model.service.SongDTO;
import com.example.Reto1Server.repository.SongRepositoryInterface;
import com.example.Reto1Server.utils.exception.song.SongNotFound;
import com.example.Reto1Server.utils.exception.song.UrlAlreadyExists;

@Service
public class SongService implements ISongService{

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
	public SongDTO getSongById(Integer id) throws SongNotFound {

		Song song = songRepository.getSongById(id);

		return convertFromDAOToDTO(song);
	}

	@Override
	public int createSong(SongDTO songDTO) throws UrlAlreadyExists {

		Song song = convertFromDTOToDAO(songDTO);

		return songRepository.createSong(song);
	}

	@Override
	public int updateSong(SongDTO songDTO) throws UrlAlreadyExists {

		Song song = convertFromDTOToDAO(songDTO);

		return songRepository.updateSong(song);
	}

	@Override
	public int deleteSong(Integer id) {

		return songRepository.deleteSong(id);
	}

	@Override
	public List<SongDTO> getFavoriteSongsByIdUser(Integer idUser) {

		List<Song> listSong = songRepository.getAllFavorites(idUser);
		List <SongDTO> response = new ArrayList<SongDTO>();

		//Transform every DAO from the list to DTO
		for(Song song : listSong){
			response.add(convertFromDAOToDTO(song));
		}
		return response;
	}
	
	@Override
	public List<SongDTO> getSongByAuthor(String author) throws SongNotFound{
		
		List<Song> listFilteredSongs = songRepository.getSongByAuthor(author);
		List<SongDTO> response = new ArrayList<SongDTO>();
		
		for(Song song : listFilteredSongs){
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
