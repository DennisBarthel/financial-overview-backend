package de.netos.auth.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenProvider {

	private final Algorithm algorithm;
	
	public TokenProvider() throws UnsupportedEncodingException {
		this.algorithm = Algorithm.HMAC512("Secret");
	}

	public String createToken(Authentication authentication) {
		UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expiry = now.plusMinutes(15);
		
		return JWT.create()
				.withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
				.withExpiresAt(Date.from(expiry.atZone(ZoneId.systemDefault()).toInstant()))
				.withSubject(principal.getUsername())
				.sign(algorithm);
	}
	
	public Optional<String> validateToken(String accessToken) {
		try {
			JWTVerifier verifier = JWT.require(algorithm)
				.build();
			
			DecodedJWT verify = verifier.verify(accessToken);
			return Optional.of(verify.getSubject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
