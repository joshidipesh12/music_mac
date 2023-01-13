package com.spring_samples.Mac.Music.repositories;

import java.util.List;
import java.util.Map;

import com.spring_samples.Mac.Music.models.Song;

public interface SongRepository {

    Song getSongById(String id);

    Song getSongByTitle(String title);

    boolean addNewSong(Song newSong);

    boolean updateSong(String songId, Map<String, String> options);

    boolean deleteSong(String songId);

    List<Song> getSongs(int limit, int page, String sortBy, boolean asc);

    List<Song> getSongsByGenre(String genre);

    public long songsCount();
}
