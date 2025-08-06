package com.hasimsolak.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hasimsolak.dto.AuthDTO;
import com.hasimsolak.dto.ErrorResponse;
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

    	try {       
    		
    		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
    		);
        
	
	        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        
	        final String jwt = jwtUtil.generateToken(userDetails);
	
	        Map<String, String> response = new HashMap<>();
	        
	        response.put("jwt", jwt);
	        
	        return ResponseEntity.ok(response); 
	        
			
		} catch (AuthenticationException e) {

            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                "Geçersiz kullanıcı adı veya şifre."
            );
            
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    
		}
        
    }
	
	@PostMapping(path = "/register")
	public ResponseEntity<?> registerUser(@RequestBody AuthDTO authDTO ) {
		try {
			
			Map<String, String> response = new HashMap<>();
			
			userService.registerUser(authDTO.getUsername(),authDTO.getPassword());
			
			response.put("message", "Kayıt başarılı.");
			
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
            	ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.CONFLICT.value(),
                    "Conflict",
                    "Kullanıcı adı zaten mevcut."
                );
                
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		}
		
	}
	
}

