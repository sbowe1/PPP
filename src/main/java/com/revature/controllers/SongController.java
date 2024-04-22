package com.revature.controllers;

import com.revature.daos.SongDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.revature.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {
    private SongDAO songDAO;
    private UserDAO userDAO;

    @Autowired
    public SongController(UserDAO userDAO, SongDAO songDAO){
        this.userDAO = userDAO;
        this.songDAO = songDAO;
    }

    // Select all songs from the song table
    @GetMapping 
    public ResponseEntity<List<Song>> getAllSongs(){
        List<Song> songs = songDAO.findAll();

        return ResponseEntity.ok(songs);
    }

    // Select one song by ID
    @GetMapping("/{songId}")
    public ResponseEntity<Object> getSongById(@PathVariable int songId){
        Optional<Song> s = songDAO.findById(songId);

        if(s.isEmpty()){
            return ResponseEntity.status(404).body("No song found with ID " + songId);
        }

        Song song = s.get();

        return ResponseEntity.ok(song);
    }

    // Select all songs played by a user
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getAllSongsByUserId(@PathVariable int userId){
        Optional<List<Song>> songList = songDAO.findAllByUserId(userId);

        if(songList.isEmpty()){
            return ResponseEntity.status(404).body("No songs found with user ID " + userId);
        }

        List<Song> songs = songList.get();

        return ResponseEntity.ok(songs);
    }

	//Insert a new song into song table
	@PostMapping("/{userid}")
	public ResponseEntity<Song> insertSong(@RequestBody Song song, @PathVariable int userId){
		User u = userDAO.findById(userId).get();

		song.setUser(u);

		Song s = songDAO.save(song);

		return ResponseEntity.status(201).body(s);
	}

	//Delete a song from the song table
	@DeleteMapping("/{songid}")
	public ResponseEntity<Object> deleteSong(@PathVariable int songID){

		Optional<Song> s = songDAO.findById(songID);

		if(s.isEmpty()){
			return ResponseEntity.status(404).body("No track with an id of ' " + songID + "' found.");
		}

		Song song = s.get();

		songDAO.deleteById(songID);

		return ResponseEntity.accepted().body("The following track has been deleted: " + song.getSongArtist() + " - " + song.getSongName() + ".");
	}
}