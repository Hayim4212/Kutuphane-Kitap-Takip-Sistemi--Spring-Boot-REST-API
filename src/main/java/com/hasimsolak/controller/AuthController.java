package com.hasimsolak.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hasimsolak.entity.User;




@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@PostMapping(path = "/register")
	public String registerUser(@RequestBody User newUser ) {
		
		
		return null;
	}

}
