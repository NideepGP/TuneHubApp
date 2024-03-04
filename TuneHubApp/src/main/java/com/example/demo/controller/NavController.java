package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class NavController {
	
	@GetMapping("/map-register")
	public String registerMapping() {
		return "register";
	}
	
	@GetMapping("/map-login")
	public String loginMapping() {
		return "login";
	}
	
	@GetMapping("/map-addsong")
	public String mapSongs() {
		return "addsongs";
	}
	
	@GetMapping("/map-payment")
	public String mapPayment() {
		return "makepayment";
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
		
}
