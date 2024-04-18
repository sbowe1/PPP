package com.revature.models;

import java.lang.String;

class Song{

	private int songId; 
	private String songName, songArtist, songAlbum;

	public Song(){
	}

	public Song(int songID, String songName, String songArtist, String songAlbum){
		this.songId = songID;
		this.songName = songName;
		this.songArtist = songArtist;
		this.songAlbum = songAlbum;
	}

	public int getSongID() { return songId; }

	public String getSongName() { return songName; }

	public String getSongArtist() { return songArtist; }

	public String getSongAlbum() { return songAlbum; }

	public void setSongID(int songID) { this.songId = songID; }

	public void setSongName(String songName) { this.songName = songName; }

	public void setSongArtist(String songArtist) { this.songArtist = songArtist; }

	public void setSongAlbum(String songAlbum) { this.songAlbum = songAlbum; }

	public String toString() { return songArtist + " - " + songName; }

}