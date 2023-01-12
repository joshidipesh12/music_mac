package com.spring_samples.Mac.Music.repository;

import java.util.List;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.spring_samples.Mac.Music.models.Song;

@Component
public class SongServices implements SongRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Song getSongById(String id) {
        Query findQuery = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(findQuery, Song.class);
    }

    @Override
    public Song getSongByTitle(String title) throws Error {
        Query findQuery = new Query(Criteria.where("title").is(title));
        return mongoTemplate.findOne(findQuery, Song.class);
    }

    @Override
    public List<Song> getSongs(int limit, int offset, String sortBy, boolean asc) {
        // songs.stream().map(Song::toString).collect(Collectors.toList()).toString();
        Query query = new Query();
        Sort.Direction sortDirection = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
        query.with(Sort.by(sortDirection, sortBy));
        query.with(PageRequest.of(offset, limit));
        return mongoTemplate.find(query, Song.class);
    }

    @Override
    public long songsCount() {
        return mongoTemplate.count(new Query(), Song.class);
    }

    @Override
    public String addNewSong(String title, String genre, int durInSec, String artistId) {
        Song newSong = new Song(title, genre, durInSec, artistId);
        mongoTemplate.insert(newSong);
        return newSong.getId();
    }

    @Override
    public void updateSong(String songId, String... options) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteSong(String songId) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Song> getSongsByGenre(String genre) {
        Query findQuery = new Query(Criteria.where("genre").is(genre));
        return mongoTemplate.find(findQuery, Song.class);
    }

}
