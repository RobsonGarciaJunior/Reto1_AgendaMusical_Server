package com.example.Reto1Server.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDAO implements UserDetails {

	private static final long serialVersionUID = 1L;

	private int idUser;
	private String name;
	private String surname;
	private String email;
	private String password;

	public UserDAO() { }

	public UserDAO(int idUser, String name, String surname, String email, String password) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}



	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer id) {
		this.idUser = id;
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

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// los permisos o autoridades que tiene. de momento nada. Aun sin roles
		return null;
	}

	@Override
	public String getUsername() {
		// esta funcion tiene que devolver el campo que hace de username. En este caso el email
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// si se hubiese expirado
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// si no esta bloqueada
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// si las credenciales no han sido expiradas
		return true;
	}

	@Override
	public boolean isEnabled() {
		// si la cuenta esta activada. Si se tiene en BBDD una columna enabled, usariamos dicha columna
		return true;
	}
}
