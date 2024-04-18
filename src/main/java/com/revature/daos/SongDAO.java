package com.revature.daos;

import com.revature.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongDAO extends JpaRepository<Song, Integer>{
}