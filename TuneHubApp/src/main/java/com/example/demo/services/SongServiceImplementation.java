package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Song;
import com.example.demo.repositories.SongRepository;

@Service
public class SongServiceImplementation implements SongService {
	
	@Autowired
	SongRepository repo;

	@Override
	public String addSongs(Song song) {
		repo.save(song);
		return "Song saved";
	}

	@Override
	public boolean songExists(String name) {
		if(repo.findByName(name)==null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public List<Song> viewSongs() {
		List<Song> songList = repo.findAll();
		return songList;
	}

	@Override
	public void updateSong(Song s) {
		repo.save(s);
	}

}
