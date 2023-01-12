package com.spring_samples.Mac.Music.repository;

import java.util.List;

import com.spring_samples.Mac.Music.models.Song;

public interface SongRepository {

    Song getSongById(String id);

    Song getSongByTitle(String title);

    String addNewSong(String title, String genre, int durInSec, String artistId);

    void updateSong(String songId, String... options);

    void deleteSong(String songId);

    List<Song> getSongs(int limit, int offset, String sortBy, boolean asc);

    List<Song> getSongsByGenre(String genre);

    public long songsCount();
}
