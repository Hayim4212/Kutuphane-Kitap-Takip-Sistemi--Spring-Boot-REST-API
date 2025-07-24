package com.hasimsolak.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hasimsolak.dto.AuthDTO;
import com.hasimsolak.entity.User;
import com.hasimsolak.security.JWTUtil;
import com.hasimsolak.service.UserService;




@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	private final UserService userService;
	
	private final JWTUtil jwtUtil;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthController(UserService userService , JWTUtil jwtUtil , AuthenticationManager authenticationManager) {
	
		this.userService = userService;
		
		this.jwtUtil =jwtUtil;
		
		this.authenticationManager = authenticationManager;
	
	}
	
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthDTO authDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
        );

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        final String jwt = jwtUtil.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        
        response.put("jwt", jwt);
        
        return ResponseEntity.ok(response); 
        
    }
	
	@PostMapping(path = "/register")
	public User registerUser(@RequestBody AuthDTO authDTO ) {
		
		
		return userService.registerUser(authDTO.getUsername(),authDTO.getPassword());
		
	}
	
}

