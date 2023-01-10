package com.spring_samples.Mac.Music.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_samples.Mac.Music.models.Song;
import com.spring_samples.Mac.Music.repository.SongRepository;

@RestController
@RequestMapping("/song")
public class SongControllers {

    @Autowired
    private SongRepository songReposit;

    @GetMapping("/")
    public ResponseEntity<Song> getSongByParam(@RequestParam Map<String, String> params) {
        Song song = null;
        if (params.containsKey("id"))
            song = songReposit.getSongById(params.get("id"));
        else if (params.containsKey("name"))
            song = songReposit.getSongByTitle(params.get("name"));
        else if (params.containsKey("title"))
            song = songReposit.getSongByTitle(params.get("title"));

        if (song == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(song);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getSongsCount() {
        return ResponseEntity.ok().body(songReposit.songsCount());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Song>> getSongsByGenre(@RequestParam String genre) {
        List<Song> songs = songReposit.getSongsByGenre(genre);
        if (songs.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(songs);
    }
}
