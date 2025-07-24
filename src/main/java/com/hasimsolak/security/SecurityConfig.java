package com.hasimsolak.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hasimsolak.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	private final JWTUtil jwtUtil;
	
	private final UserRepository userRepository;
	
	private final JWTFilter jwtFilter;
	
	private final AuthenticationProvider authenticationProvider;
	
	
	public SecurityConfig(JWTUtil jwtUtil , UserRepository userRepository , JWTFilter jwtFilter , AuthenticationProvider authenticationProvider) {
		
		this.jwtUtil = jwtUtil;
		
		this.userRepository = userRepository;
		
		this.jwtFilter = jwtFilter;
		
		this.authenticationProvider = authenticationProvider;
		
	}
	

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/auth/**")
			.permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}
	
	
}
