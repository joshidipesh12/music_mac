package com.spring_samples.Mac.Music.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_samples.Mac.Music.models.Artist;
import com.spring_samples.Mac.Music.repositories.ArtistRepository;

/**
 * REST Controller for Artists Entity Related Endpoints.
 * Base URL: <b>/artist</b>
 * <p>
 * Autowired with {@link ArtistRepository}
 * </p>
 */
@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    private ArtistRepository artistReposit;

    /**
     * Get Artist Details with artist id/name.
     * 
     * @param params - map containing either id, name or title
     * @return JSON response with artist detains
     */
    @GetMapping("/")
    public ResponseEntity<Artist> getartistByParam(@RequestParam final Map<String, String> params) {
        Artist artist = null;
        if (params.containsKey("id"))
            artist = artistReposit.getArtistById(params.get("id"));
        else if (params.containsKey("name"))
            artist = artistReposit.getArtistByName(params.get("name"));

        if (artist == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(artist);
    }

    /**
     * Endpoint to get current Artist Count.
     * 
     * @return Count {@link Long} of total Artists in the DB
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getArtistsCount() {
        return ResponseEntity.ok().body(artistReposit.artistsCount());
    }

    /**
     * Get the list of all Artists. Suppoted features include, Pagination with limit
     * &
     * page using {@link PageRequest} and Sorting results using {@link Sort}
     * (default "followers").
     * 
     * @param params - {@link Map} of request parameters ("limit", "page",
     *               "sortBy" & "ascending")
     * @return {@link List} of Artists as Response Entity
     */
    @GetMapping("/list")
    public ResponseEntity<List<Artist>> getArtists(@RequestParam final Map<String, String> params) {
        final int limit = Integer.parseInt(params.getOrDefault("limit", "5"));
        final int page = Integer.parseInt(params.getOrDefault("page", "0"));
        final String sortBy = params.getOrDefault("sortBy", "followers");
        final boolean asc = Boolean.parseBoolean(params.getOrDefault("ascending", "false"));
        return ResponseEntity.ok().body(artistReposit.getArtists(limit, page, sortBy, asc));
    }

    /**
     * Add a new artist with provided details to the database.
     * 
     * @param body - {@link Map} of request parameters ("title", "genre", "durInSec"
     *             & "artistId")
     * @return ID of the {@link Artist} newly added
     */
    @PostMapping("/")
    public ResponseEntity<String> addNewartist(@RequestBody final Map<String, String> body) {
        if (body == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Please provide proper details");

        for (final String param : new String[] { "name", "gender", "bio", "url", "age" })
            if (!body.containsKey(param)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(String.format("Please provide proper details. [%s not found!]", param));
            }

        Artist newartist = new Artist(body.get("name"), body.get("gender"), body.get("bio"), body.get("url"),
                Integer.parseInt(body.get("age")));
        try {
            if (artistReposit.addNewArtist(newartist))
                return ResponseEntity.ok(newartist.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unable to add artist.");
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
