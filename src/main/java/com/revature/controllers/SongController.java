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

    // Select song by ID
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
}