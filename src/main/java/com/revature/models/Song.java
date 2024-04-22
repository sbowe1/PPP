package com.revature.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.lang.String;
import java.time.LocalDateTime;

@Entity
@Table(name="songs")
@Component
public class Song{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int songId;

	@Column(nullable = false)
	private String songName;

	@Column(nullable = false)
	private String songArtist;

	private String songAlbum;

	@CreationTimestamp
	private LocalDateTime lastPlayed;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId")
	private User user;

	//Constructors
	public Song(){
	}

	public Song(int songID, String songName, String songArtist, String songAlbum, LocalDateTime lastPlayed, User user){
		this.songId = songID;
		this.songName = songName;
		this.songArtist = songArtist;
		this.songAlbum = songAlbum;
		this.lastPlayed = lastPlayed;
		this.user = user;
	}

	//Getters and Setters
	public int getSongID() { return songId; }

	public String getSongName() { return songName; }

	public String getSongArtist() { return songArtist; }

	public String getSongAlbum() { return songAlbum; }

	public LocalDateTime getLastPlayed() { return lastPlayed; }

	public User getUser() { return user; }

	public void setSongID(int songID) { this.songId = songID; }

	public void setSongName(String songName) { this.songName = songName; }

	public void setSongArtist(String songArtist) { this.songArtist = songArtist; }

	public void setSongAlbum(String songAlbum) { this.songAlbum = songAlbum; }

	public void setLastPlayed(LocalDateTime lastPlayed) { this.lastPlayed = lastPlayed; }

	public void setUser(User user) { this.user = user; }

	//Custom toString
	public String toString() { return songArtist + " - " + songName; }

}