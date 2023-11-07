package com.example.Reto1Server.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reto1Server.model.service.UserDTO;
import com.example.Reto1Server.security.configuration.JwtTokenUtil;
import com.example.Reto1Server.security.model.AuthRequest;
import com.example.Reto1Server.security.model.AuthResponse;
import com.example.Reto1Server.security.model.AuthUpdatePassword;
import com.example.Reto1Server.security.model.UserDAO;
import com.example.Reto1Server.security.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api")
public class AuthController {

	@Autowired 
	AuthenticationManager authenticationManager;

	@Autowired 
	JwtTokenUtil jwtUtil;

	@Autowired
	AuthService authService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			// esta es la funcion que va a intentar identificarse, dado el username y la password introducida
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
					);
			// devolvera un objeto de tipo authenticacion de las que de momento nos interesa el "principal". El principal contiene los datos del usuario
			// por lo que lo convertimos a su modelo real de BD para tener todos sus campos
			UserDAO user = (UserDAO) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(accessToken);

			return ResponseEntity.ok().body(response);

		} catch (BadCredentialsException ex) {
			// esta excepción salta y estamos devolviendo un 401. se podria cambiar pero cuidado con lo que se devuelve al fallar el login etc
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}


	@PostMapping("/auth/signup")
	public ResponseEntity<?> signIn(@RequestBody @Valid AuthRequest request) {
		// TODO solo esta creado en el caso de que funcione. Si no es posible que de 500 o 401.
		// aqui hacer lo que sea preciso

		// vamos a cifrar la contrasenia aqui, ya que no queremos andar dando vueltas con la contraseña sin encriptar si no es preciso
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(request.getPassword());

		// creamos el usuario en DB
		UserDAO userDAO = new UserDAO(request.getIdUser(), request.getName(), request.getSurname(), request.getEmail(), password);
		UserDTO userDTO = convertFromDAOToDTO(userDAO);
		return new ResponseEntity<Integer>(authService.create(userDTO), HttpStatus.CREATED);
	}

	//	TODO ACTUALIZAR CONTRASENNA
	@PutMapping("users/me")
	public ResponseEntity<?> updateUserPassword(Authentication authentication, @RequestBody AuthUpdatePassword authUpdatePassword){

		UserDAO userDetails = (UserDAO) authentication.getPrincipal();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		//Obtenemos las contrasennas del cuerpo de la peticion
		String newPassword = authUpdatePassword.getNewPassword();
		String oldPassword =  authUpdatePassword.getOldPassword();

		//Ciframos la nueva contrasenna
		String newPasswordEncrypted = passwordEncoder.encode(newPassword);
		userDetails.setPassword(newPasswordEncrypted);
		UserDTO userDTO = convertFromDAOToDTO(userDetails);
		return new ResponseEntity<>(authService.updateUserPassword(userDTO, oldPassword),HttpStatus.NO_CONTENT);
	}

	//CONVERTS
	private UserDTO convertFromDAOToDTO(UserDAO userDetails) {

		UserDTO response = new UserDTO(
				userDetails.getIdUser(),
				userDetails.getName(),
				userDetails.getSurname(),
				userDetails.getEmail(),
				userDetails.getPassword()
				);
		return response;
	}

	@DeleteMapping("/users/me")
	public ResponseEntity<?> deleteUser(Authentication authentication) {

		UserDAO userDetails = (UserDAO) authentication.getPrincipal();

		return new ResponseEntity<>(authService.deleteUser(userDetails.getIdUser()),HttpStatus.NO_CONTENT);
	}

}
