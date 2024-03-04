package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Song;

public interface SongService {
	public String addSongs(Song song);
	
	public boolean songExists(String name);
	
	public List<Song> viewSongs();

	public void updateSong(Song s);
}
