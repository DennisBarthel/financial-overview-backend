package de.netos.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.netos.auth.entity.User;
import de.netos.auth.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = repository
				.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Test"));
		
		return new UserPrincipal(user.getEmail(), user.getPassword());
	}
}
