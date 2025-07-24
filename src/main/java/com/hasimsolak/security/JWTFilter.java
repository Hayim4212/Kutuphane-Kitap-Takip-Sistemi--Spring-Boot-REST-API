package com.hasimsolak.security;


import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	
	private final JWTUtil jwtUtil;

	private final UserDetailsService userDetailsService;
	
	public JWTFilter(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String header = request.getHeader("Authorization");
		final String jwt;
		final String username;
		
		if(header==null || header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		};
		
		jwt = header.substring(7);
		username = jwtUtil.findUsername(jwt);
		if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if (jwtUtil.tokenControl(jwt, userDetails)) {
				
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null
						,userDetails.getAuthorities());
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
