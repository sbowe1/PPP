package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.lang.String;

@Entity
@Table(name="songs")
@Component
class Song{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int songId;

	@Column(nullable = false)
	private String songName;

	@Column(nullable = false)
	private String songArtist;

	private String songAlbum;

	//Constructors
	public Song(){
	}

	public Song(int songID, String songName, String songArtist, String songAlbum){
		this.songId = songID;
		this.songName = songName;
		this.songArtist = songArtist;
		this.songAlbum = songAlbum;
	}

	//Getters and Setters
	public int getSongID() { return songId; }

	public String getSongName() { return songName; }

	public String getSongArtist() { return songArtist; }

	public String getSongAlbum() { return songAlbum; }

	public void setSongID(int songID) { this.songId = songID; }

	public void setSongName(String songName) { this.songName = songName; }

	public void setSongArtist(String songArtist) { this.songArtist = songArtist; }

	public void setSongAlbum(String songAlbum) { this.songAlbum = songAlbum; }

	//Custom toString
	public String toString() { return songArtist + " - " + songName; }

}