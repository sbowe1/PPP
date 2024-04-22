package com.revature.daos;

import com.revature.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongDAO extends JpaRepository<Song, Integer>{
	
    // Defining a new method that finds all songs by userId
    public Optional<List<Song>> findAllByUserId(int userId);
}