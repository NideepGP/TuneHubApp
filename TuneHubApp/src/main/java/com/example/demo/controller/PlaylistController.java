package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PlaylistController {
	
	@Autowired
	PlaylistService pserv;
	
	@Autowired
	SongService songServ;
	
	@GetMapping("/map-createplaylist")
	public String createPlaylist(Model model) {
		//Fetching all songs
		List<Song> slist = songServ.viewSongs();
		
		//Adding it to model
		model.addAttribute("songs", slist);
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		pserv.addPlaylist(playlist);
		List<Song> songList = playlist.getSong();
		for(Song s : songList) {
			s.getPlaylist().add(playlist);
			songServ.updateSong(s);
		}
		return "playlistsuccess";
	}
	
	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> p_list = pserv.fetchPlaylist();
		model.addAttribute("playlist", p_list);
		return "viewplaylist";
	}
	
	
}
