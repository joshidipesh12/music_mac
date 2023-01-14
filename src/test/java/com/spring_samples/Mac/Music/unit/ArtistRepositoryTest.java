package com.spring_samples.Mac.Music.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Answers.RETURNS_MOCKS;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring_samples.Mac.Music.models.Artist;
import com.spring_samples.Mac.Music.repositories.ArtistRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
class ArtistRepositoryTest {

    @Mock
    @Autowired
    MongoTemplate mongoTemplate;

    @InjectMocks
    @Autowired
    ArtistRepository artistReposit;

    final static Artist testArtist = new Artist("Test Artist", "female", "Test Bio", "test.url.this", 30);

    @Test
    void ShouldAddNewArtistWhenProvidedFields() {
        when(mongoTemplate.insert(testArtist)).thenReturn(testArtist);
        assertEquals(true, artistReposit.addNewArtist(testArtist));
    }

    @Test
    void ShouldDeleteArtistWhenProvidedId() {
        Query testQuery = new Query(Criteria.where("id").is(testArtist.getId()));
        when(mongoTemplate.findAndRemove(testQuery, Artist.class)).thenReturn(testArtist);
        assertEquals(true, artistReposit.deleteArtist(testArtist.getId()));
    }

    @Test
    void ShouldReturnArtistWhenProvidedId() {
        Query testQuery = new Query(Criteria.where("id").is(testArtist.getId()));
        when(mongoTemplate.findOne(testQuery, Artist.class)).thenReturn(testArtist);
        assertEquals(testArtist, artistReposit.getArtistById(testArtist.getId()));
    }

    @Test
    void ShouldReturnArtistWhenProvideName() {
        Query testQuery = new Query(Criteria.where("name").is(testArtist.getName()));
        when(mongoTemplate.findOne(testQuery, Artist.class)).thenReturn(testArtist);
        assertEquals(testArtist, artistReposit.getArtistByName(testArtist.getName()));
    }

    @Test
    void ShouldReturnListOfArtists() {
        Query testQuery = new Query();
        when(mongoTemplate.find(testQuery, Artist.class)).thenReturn(new ArrayList<Artist>());
        assertInstanceOf(List.class, artistReposit.getArtists(0, 0, "age", false));
    }

    @Test
    void ShouldReturnArtistCount() {
        when(mongoTemplate.count(new Query(), Artist.class)).thenReturn((long) 21);
        assertEquals(21, artistReposit.artistsCount());
    }

    @Test
    void ShouldUpdateArtistObjectWhenProvidedFields() {
        Query testQuery = new Query(Criteria.where("id").is(testArtist.getId()));
        Update testUpdate = new Update();
        testUpdate.set("age", testArtist.getAge() + 1);
        when(mongoTemplate.updateFirst(testQuery, testUpdate, Artist.class)).thenAnswer(RETURNS_MOCKS);
        assertEquals(true, artistReposit.updateArtist(testArtist.getId(), new HashMap<String, String>()));
    }
}
