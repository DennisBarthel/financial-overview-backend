package de.netos.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.netos.auth.exception.LoginException;
import de.netos.auth.login.LoginRequest;
import de.netos.auth.login.LoginResponse;
import de.netos.auth.service.AuthService;
import de.netos.auth.signup.SignUpRequest;
import de.netos.auth.signup.SignUpResponse;

@RestController
@RequestMapping(path = "/auth", consumes = "application/json")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request) {
		LoginResponse response = authService.loginUser(request);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/register")
	public ResponseEntity<SignUpResponse> registerUser(@Valid @RequestBody SignUpRequest request) throws LoginException {
		String registerUser = authService.registerUser(request);
		
		return ResponseEntity.ok(new SignUpResponse(true));
	}
}
