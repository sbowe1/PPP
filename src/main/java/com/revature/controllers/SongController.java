package com.revature.controllers;

import com.revature.daos.SongDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.revature.models.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @GetMapping("/song/{songId}")
    public ResponseEntity<Object> getSongById(@PathVariable int songId){
        Optional<Song> s = songDAO.findById(songId);

        if(s.isEmpty()){
            return ResponseEntity.status(404).body("No song found with ID " + songId);
        }

        Song song = s.get();

        return ResponseEntity.ok(song);
    }

    // Select all songs played by a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAllSongsByUserId(@PathVariable int userId){
        Optional<User> u = userDAO.findById(userId);
        if(u.isEmpty()){
            return ResponseEntity.status(404).body("No user found with user ID " + userId);
        }

        Optional<List<Song>> songs = songDAO.findAllByUser(u.get());

        if(songs.isEmpty()){
            return ResponseEntity.status(404).body("User " + userId + " has not listened to any songs");
        }

        return ResponseEntity.ok(songs.get());
    }

	//Insert a new song into song table
	@PostMapping("/{userId}")
	public ResponseEntity<Song> insertSong(@RequestBody Song song, @PathVariable int userId){
		User u = userDAO.findById(userId).get();

		song.setUser(u);

		Song s = songDAO.save(song);

		return ResponseEntity.status(201).body(s);
	}

	//Delete a song from the song table
	@DeleteMapping("/{songID}")
	public ResponseEntity<Object> deleteSong(@PathVariable int songID){

		Optional<Song> s = songDAO.findById(songID);

		if(s.isEmpty()){
			return ResponseEntity.status(404).body("No track with an id of ' " + songID + "' found.");
		}

		Song song = s.get();

		songDAO.deleteById(songID);

		return ResponseEntity.accepted().body("The following track has been deleted: " + song.getSongArtist() + " - " + song.getSongName() + ".");
	}

	//Extra Functionality

	// Update Methods for each property of a Song

	//Update Song Name
    @PatchMapping("/{songId}/name")
    public ResponseEntity<Object> updateSongName(@PathVariable int songId, @RequestBody String newSongName){
        Optional<Song> s = songDAO.findById(songId);

        if(s.isEmpty()){
            return ResponseEntity.status(404).body("No track with an id of ' " + songId + "' found.");
        }

        Song song = s.get();
        song.setSongName(newSongName);
        songDAO.save(song);

        return ResponseEntity.accepted().body(song);
    }

	//Update Song Artist
    @PatchMapping("/{songId}/artist")
    public ResponseEntity<Object> updateSongArtist(@PathVariable int songId, @RequestBody String newArtistName){
        Optional<Song> s = songDAO.findById(songId);

        if(s.isEmpty()){
            return ResponseEntity.status(404).body("No track with an id of ' " + songId + "' found.");
        }

        Song song = s.get();
        song.setSongArtist(newArtistName);
        songDAO.save(song);

        return ResponseEntity.accepted().body(song);
    }

	//Update Song Album
    @PatchMapping("/{songId}/album")
    public ResponseEntity<Object> updateSongAlbum(@PathVariable int songId, @RequestBody String newAlbumName){
        Optional<Song> s = songDAO.findById(songId);

        if(s.isEmpty()){
            return ResponseEntity.status(404).body("No track with an id of ' " + songId + "' found.");
        }

        Song song = s.get();
        song.setSongAlbum(newAlbumName);
        songDAO.save(song);

        return ResponseEntity.accepted().body(song);
    }

	//Update Last Played Date
    @PatchMapping("/{songId}/last-played")
    public ResponseEntity<Object> updateLastPlayed(@PathVariable int songId){
        Optional<Song> s = songDAO.findById(songId);

        if(s.isEmpty()){
            return ResponseEntity.status(404).body("No track with an id of ' " + songId + "' found.");
        }

        Song song = s.get();
        song.setLastPlayed(LocalDateTime.now());
        songDAO.save(song);

        return ResponseEntity.accepted().body(song);
    }	

	//increment playCount
    @PatchMapping("/{songId}/play-count")
    public ResponseEntity<Object> incrementPlayCount(@PathVariable int songId){
        Optional<Song> s = songDAO.findById(songId);

        if(s.isEmpty()){
            return ResponseEntity.status(404).body("No track with an id of ' " + songId + "' found.");
        }

        Song song = s.get();
        song.incPlayCount();
        songDAO.save(song);

        return ResponseEntity.accepted().body(song);
    }	

	//set playCount
    @PatchMapping("/{songId}/new")
    public ResponseEntity<Object> setPlayCount(@PathVariable int songId, @RequestBody int newPlayCount){
        Optional<Song> s = songDAO.findById(songId);

        if(s.isEmpty()){
            return ResponseEntity.status(404).body("No track with an id of ' " + songId + "' found.");
        }

        Song song = s.get();
        song.setPlayCount(newPlayCount);
        songDAO.save(song);

        return ResponseEntity.accepted().body(song);
    }	

	//Business Logic-Like Function: Play Song
	//public void playSong(@PathVariable int songId){
	//	updateLastPlayed(songId, LocalDateTime.now());
	//	updatePlayCount(songId);		
	//}	


}