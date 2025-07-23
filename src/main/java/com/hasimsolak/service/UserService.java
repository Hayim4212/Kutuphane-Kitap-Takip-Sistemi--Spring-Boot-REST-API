package com.hasimsolak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hasimsolak.entity.User;
import com.hasimsolak.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User registerUser(User newUser) {
		
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanılıyor.");
        }
        
        return userRepository.save(newUser);
        
	}
	
    
    public UserDetailsService userDetailsService() {
    	
        return username -> userRepository.findByUsername(username)
        		.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
   
    }

}
