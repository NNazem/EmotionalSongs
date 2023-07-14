package com.example.emotionalsongback.repository;

import com.example.emotionalsongback.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
}
