package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {

	@Autowired
	UsersService userv;

	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) 
	{
		boolean userStatus = userv.emailExists(user.getEmail());

		if(userStatus==false) {
			userv.addUser(user);
			return "registersuccess";
		}
		else {
			return "registerfail";
		}
	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		boolean loginStatus = userv.validateUser(email, password);
		
		if(loginStatus) {
		
			session.setAttribute("email", email);
			String name = userv.getUser(email).getUsername();
			session.setAttribute("name", name);
			
			if(userv.getRole(email).equals("admin")) {
				return "adminhome";
			}
			else {
				return "customerhome";
			}
		}
		else {
			return "loginfail";
		}
	}
	
		
}
