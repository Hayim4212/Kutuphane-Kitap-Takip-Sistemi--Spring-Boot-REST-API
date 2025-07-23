package com.hasimsolak.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hasimsolak.entity.User;
import com.hasimsolak.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/register")
	public User registerUser(@RequestBody User newUser ) {
		
		return userService.registerUser(newUser) ;
	}

	
}
