package com.example.Reto1Server.model.controller.song;

public class SongPutRequest {

	private Integer idSong;
	private String title;
	private String author;
	private String url;
	
	public SongPutRequest() {}
	
	public SongPutRequest(Integer idSong, String title, String author, String url) {
		super();
		this.idSong = idSong;
		this.title = title;
		this.author = author;
		this.url = url;
	}

	public Integer getIdSong() {
		return idSong;
	}

	public void setIdSong(Integer idSong) {
		this.idSong = idSong;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "SongPutRequest [idSong=" + idSong + ", title=" + title + ", author=" + author + ", url=" + url + "]";
	}
	
}
