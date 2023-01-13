package com.spring_samples.Mac.Music.Services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.spring_samples.Mac.Music.models.Song;
import com.spring_samples.Mac.Music.repositories.SongRepository;

@Component
public class SongService implements SongRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Song getSongById(String id) {
        Query findQuery = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(findQuery, Song.class);
    }

    @Override
    public Song getSongByTitle(String title) {
        Query findQuery = new Query(Criteria.where("title").is(title));
        return mongoTemplate.findOne(findQuery, Song.class);
    }

    @Override
    public List<Song> getSongs(int limit, int offset, String sortBy, boolean asc) {
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
    public boolean addNewSong(Song newSong) {
        Song savedSong = mongoTemplate.insert(newSong);
        return savedSong != null;
    }

    @Override
    public boolean updateSong(String songId, Map<String, String> options) {
        Query query = new Query(Criteria.where("id").is(songId));
        Update updateQuery = new Update();
        for (Map.Entry<String, String> entry : options.entrySet()) {
            updateQuery.set(entry.getKey(), entry.getValue());
        }
        UpdateResult result = mongoTemplate.updateFirst(query, updateQuery,
                Song.class);
        return result != null;
    }

    @Override
    public boolean deleteSong(String songId) {
        Query query = new Query(Criteria.where("id").is(songId));
        Song result = mongoTemplate.findAndRemove(query, Song.class);
        return result != null;
    }

    @Override
    public List<Song> getSongsByGenre(String genre) {
        Query findQuery = new Query(Criteria.where("genre").is(genre));
        return mongoTemplate.find(findQuery, Song.class);
    }

}
