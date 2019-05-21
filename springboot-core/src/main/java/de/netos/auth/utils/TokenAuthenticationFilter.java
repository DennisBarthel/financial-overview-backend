package de.netos.auth.utils;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.web.filter.OncePerRequestFilter;

import de.netos.auth.service.CustomUserDetailsService;
import de.netos.auth.service.TokenProvider;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Optional<String> accessToken = getAccessTokenFromRequest(request);
		
		accessToken.ifPresent(token -> {
			Optional<String> subject = tokenProvider.validateToken(token);
			if (subject.isPresent()) {
				String s = subject.get();
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(s);
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		});
		
		filterChain.doFilter(request, response);
	}

	private Optional<String> getAccessTokenFromRequest(HttpServletRequest request) {
		Authentication authentication = new BearerTokenExtractor().extract(request);
		
		if (authentication != null) {
			return Optional.ofNullable((String) authentication.getPrincipal());
		} else {
			return Optional.empty();
		}
	}
}
