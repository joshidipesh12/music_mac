package com.spring_samples.Mac.Music.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Answers.RETURNS_MOCKS;
import static org.mockito.Mockito.*;

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
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.data.mongodb.core.query.Criteria;
import com.spring_samples.Mac.Music.models.Song;
import com.spring_samples.Mac.Music.repositories.SongRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
class SongRepositoryTest {

    @Mock
    @Autowired
    MongoTemplate mongoTemplate;

    @InjectMocks
    @Autowired
    SongRepository songReposit;

    final static Song testSong = new Song("Test Song", "Test Genre", 500, "Test Song");

    @Test
    void ShouldAddNewSongWhenProvidedFields() {
        when(mongoTemplate.insert(testSong)).thenReturn(testSong);
        assertEquals(true, songReposit.addNewSong(testSong));
    }

    @Test
    void ShouldDeleteSongWhenProvidedId() {
        Query testQuery = new Query(Criteria.where("id").is(testSong.getId()));
        when(mongoTemplate.findAndRemove(testQuery, Song.class)).thenReturn(testSong);
        assertEquals(true, songReposit.deleteSong(testSong.getId()));
    }

    @Test
    void ShouldReturnSongWhenProvidedId() {
        Query testQuery = new Query(Criteria.where("id").is(testSong.getId()));
        when(mongoTemplate.findOne(testQuery, Song.class)).thenReturn(testSong);
        assertEquals(testSong, songReposit.getSongById(testSong.getId()));
    }

    @Test
    void ShouldReturnSongWhenProvidedTitle() {
        Query testQuery = new Query(Criteria.where("title").is(testSong.getTitle()));
        when(mongoTemplate.findOne(testQuery, Song.class)).thenReturn(testSong);
        assertEquals(testSong, songReposit.getSongByTitle(testSong.getTitle()));
    }

    @Test
    void ShouldReturnListOfSongsWhenProvidedFields() {
        Query testQuery = new Query();
        when(mongoTemplate.find(testQuery, Song.class)).thenReturn(new ArrayList<Song>());
        assertInstanceOf(List.class, songReposit.getSongs(0, 0, "views", false));
    }

    @Test
    void ShouldReturnListOfSongsWhenProvidedGenre() {
        Query testQuery = new Query(Criteria.where("genre").is(testSong.getGenre()));
        when(mongoTemplate.find(testQuery, Song.class)).thenReturn(new ArrayList<Song>());
        assertInstanceOf(List.class, songReposit.getSongsByGenre(testSong.getGenre()));
    }

    @Test
    void ShouldReturnSongsCount() {
        when(mongoTemplate.count(new Query(), Song.class)).thenReturn((long) 100);
        assertEquals(100, songReposit.songsCount());
    }

    @Test
    void ShouldUpdateArtistObjectWhenProvidedFields() {
        Query testQuery = new Query(Criteria.where("id").is(testSong.getId()));
        Update testUpdate = new Update();
        testUpdate.set("likes", testSong.getLikes() + 1);
        when(mongoTemplate.updateFirst(testQuery, testUpdate, Song.class)).thenAnswer(RETURNS_MOCKS);
        assertEquals(true, songReposit.updateSong(testSong.getId(), new HashMap<String, String>()));
    }
}
