package com.spring_samples.Mac.Music.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_samples.Mac.Music.models.Song;
import com.spring_samples.Mac.Music.repository.SongRepository;

/**
 * REST Controller for Songs Entity Related Endpoints.
 * Base URL: <b>/song</b>
 * <p> Autowired with {@link SongRepository} </p>
 */
@RestController
@RequestMapping("/song")
public class SongControllers {

    @Autowired
    private SongRepository songReposit;

    /**
     * Get Song Details with song id/title.
     * @param params - map containing either id, name or title
     * @return JSON response with song detains
     */
    @GetMapping("/")
    public ResponseEntity<Song> getSongByParam(@RequestParam final Map<String, String> params) {
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

    /**
     * Endpoint to get current Song Count.
     * @return Count {@link Long} of total songs in the DB
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getSongsCount() {
        return ResponseEntity.ok().body(songReposit.songsCount());
    }

    /**
     * Lists all songs belonging to provided genre.
     * @param genre
     * @return Array of Songs.
     */
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Song>> getSongsByGenre(@PathVariable final String genre) {
        final List<Song> songs = songReposit.getSongsByGenre(genre);
        if (songs.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(songs);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Song>> getSongs(@RequestParam final Map<String, String> params) {
        final int limit = Integer.parseInt(params.getOrDefault("limit", "20"));
        final int offset = Integer.parseInt(params.getOrDefault("offset", "0"));
        final String sortBy = params.getOrDefault("sortBy", "views");
        final boolean asc = Boolean.parseBoolean(params.getOrDefault("ascending", "false"));
        return ResponseEntity.ok().body(songReposit.getSongs(limit, offset, sortBy, asc));
    }

    @PostMapping("/")
    public ResponseEntity<String> addNewSong(@RequestBody final Map<String, String> body) {
        if (body == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Please provide proper details");

        for (final String param : new String[] { "title", "genre", "durInSec", "artistId" }) 
            if (!body.containsKey(param)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(String.format("Please provide proper details. [%s not found!]", param));
            }

        try {
            final String songId = songReposit.addNewSong(body.get("title"), body.get("genre"),
                    Integer.parseInt(body.get("durInSec")), body.get("artistId"));
            return ResponseEntity.ok(songId);
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
