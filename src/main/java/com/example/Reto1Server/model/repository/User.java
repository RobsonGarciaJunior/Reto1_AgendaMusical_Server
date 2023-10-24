package com.example.Reto1Server.model.repository;

public class User {
	
	private int idUser;
	private String name;
	private String surname;
	private String email;
	private String password;
	
	public User() {
		
	}
	public User(int idUser, String name, String surname, String email, String password) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", surname=" + surname + ", email=" + email + ", password="
				+ password + "]";
	}
	
}
