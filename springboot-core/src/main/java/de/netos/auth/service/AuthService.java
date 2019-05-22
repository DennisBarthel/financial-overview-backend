package de.netos.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.netos.auth.AuthProvider;
import de.netos.auth.entity.User;
import de.netos.auth.exception.LoginException;
import de.netos.auth.exception.LoginException.ErrorMessage;
import de.netos.auth.login.LoginRequest;
import de.netos.auth.login.LoginResponse;
import de.netos.auth.repository.UserRepository;
import de.netos.auth.signup.SignUpRequest;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenProvider tokenProvider;

	public LoginResponse loginUser(LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = tokenProvider.createToken(authentication);
		return new LoginResponse(token);
	}
	
	public void registerUser(SignUpRequest signUp) throws LoginException {
		if (userRepository.existsByEmail(signUp.getEmail())) {
			throw new LoginException(ErrorMessage.EMAIL_ALREADY_EXISTS, "Email address already used.");
		}
		
		User user = new User();
		user.setFirstname(signUp.getFirstname());
		user.setLastname(signUp.getLastname());
		user.setEmail(signUp.getEmail());
		user.setPassword(passwordEncoder.encode(signUp.getPassword()));
		user.setAuthProvider(AuthProvider.LOCAL);
		
		userRepository.save(user);
	}
}
