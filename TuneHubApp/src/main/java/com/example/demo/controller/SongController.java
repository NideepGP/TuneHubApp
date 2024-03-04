package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SongController {

	@Autowired
	SongService sserv;
	
	@Autowired
	UsersService userv;

	@PostMapping("/addSong")
	public String addSongs(@ModelAttribute Song song) {
		boolean songStatus = sserv.songExists(song.getName());
		if(songStatus==false) {
			String msg = sserv.addSongs(song);
			System.out.println(msg);
			return "addsongsuccess";
		}
		else {
			return "addsongfail";
		}
	}

	@GetMapping("/viewsong")
	public String viewSongs(Model model) {
		List<Song> songList = sserv.viewSongs();
		model.addAttribute("songs", songList);
		return "viewsongs";
	}
	
	//customer explore songs
	@GetMapping("/exploreSongs")
	public String exploreSongs(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		Users user = userv.getUser(email);
		boolean premiumStatus = user.isPremium();
		if(premiumStatus) {
			List<Song> songList = sserv.viewSongs();
			model.addAttribute("songs", songList);
			return "custviewsongs";
		}
		else {
			return "makepayment";
		}
	}

}
