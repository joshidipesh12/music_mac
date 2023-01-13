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
import com.spring_samples.Mac.Music.models.Artist;
import com.spring_samples.Mac.Music.repositories.ArtistRepository;

@Component
public class ArtistService implements ArtistRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Artist getArtistById(String id) {
        Query findQuery = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(findQuery, Artist.class);
    }

    @Override
    public Artist getArtistByName(String name) {
        Query findQuery = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(findQuery, Artist.class);
    }

    @Override
    public boolean addNewArtist(Artist newArtist) {
        Artist savedArtist = mongoTemplate.insert(newArtist);
        return savedArtist != null;
    }

    @Override
    public boolean updateArtist(String artistId, Map<String, String> options) {
        Query query = new Query(Criteria.where("id").is(artistId));
        Update updateQuery = new Update();
        for (Map.Entry<String, String> entry : options.entrySet()) {
            updateQuery.set(entry.getKey(), entry.getValue());
        }
        UpdateResult result = mongoTemplate.updateFirst(query, updateQuery,
                Artist.class);
        return result != null;
    }

    @Override
    public boolean deleteArtist(String artistId) {
        Query query = new Query(Criteria.where("id").is(artistId));
        Artist result = mongoTemplate.findAndRemove(query, Artist.class);
        return result != null;
    }

    @Override
    public List<Artist> getArtists(int limit, int offset, String sortBy, boolean asc) {
        Query query = new Query();
        Sort.Direction sortDirection = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
        query.with(Sort.by(sortDirection, sortBy));
        query.with(PageRequest.of(offset, limit));
        return mongoTemplate.find(query, Artist.class);
    }

    @Override
    public long artistsCount() {
        return mongoTemplate.count(new Query(), Artist.class);
    }
}
