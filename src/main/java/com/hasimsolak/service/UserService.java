package com.hasimsolak.service;


import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hasimsolak.entity.User;
import com.hasimsolak.repository.UserRepository;

@Service
public class UserService {
	
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		
		this.passwordEncoder = passwordEncoder;
		
	}
	
	
	public User registerUser(String username, String password) {
		
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanılıyor.");
        }
        User newUser = new User();
        
        newUser.setUsername(username);
        
        newUser.setPassword(passwordEncoder.encode(password));
        
        return userRepository.save(newUser);
        
	}
	

    
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
}
