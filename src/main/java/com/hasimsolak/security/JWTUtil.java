package com.hasimsolak.security;


import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTUtil {
	
	private final String SECRET_KEY = "KL/Ui02lHV1MTXSGcHi+NVp/DTNlDfN5RzbYCWODBTffo8NkSxld1PXCXDYCjdQ8dLkc/eUYy1FqnzhnA7NjsQ==";
	
	public String findUsername(String token) {
		
		return exportToken(token , Claims::getSubject);
		
	}

	private <T> T exportToken(String token, Function<Claims, T> clatimsTFunction) {
	
		final Claims claims = Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build().parseClaimsJws(token).getBody();
		
		return clatimsTFunction.apply(claims);
	
	}

	private java.security.Key getKey() {
		
		byte[] key = Decoders.BASE64.decode(SECRET_KEY);

		return Keys.hmacShaKeyFor(key);
	}

	public boolean tokenControl(String jwt, UserDetails userDetails) {
		final String username = findUsername(jwt);
		return (username.equals(userDetails.getUsername()) && !exportToken(jwt , Claims::getExpiration).before(new Date()));
	}

	public String generateToken(UserDetails user) {
		return Jwts.builder()
				.setClaims(new HashMap<>())
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getKey() , SignatureAlgorithm.HS256)
				.compact();
	}


}
