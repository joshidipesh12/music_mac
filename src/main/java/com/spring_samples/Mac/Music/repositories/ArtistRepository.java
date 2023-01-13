package com.spring_samples.Mac.Music.repositories;

import java.util.List;
import java.util.Map;

import com.spring_samples.Mac.Music.models.Artist;

public interface ArtistRepository {

    Artist getArtistById(String id);

    Artist getArtistByName(String name);

    boolean addNewArtist(Artist artist);

    boolean updateArtist(String artistId, Map<String, String> options);

    boolean deleteArtist(String artistId);

    List<Artist> getArtists(int limit, int offset, String sortBy, boolean asc);

    public long artistsCount();
}