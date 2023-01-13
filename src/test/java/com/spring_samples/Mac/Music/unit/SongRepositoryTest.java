package com.spring_samples.Mac.Music.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

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
import org.springframework.test.context.junit4.SpringRunner;

import com.spring_samples.Mac.Music.models.Song;
import com.spring_samples.Mac.Music.repositories.SongRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})

class SongRepositoryTest {

    @Mock
    @Autowired
    MongoTemplate mongoTemplate;

    @InjectMocks
    @Autowired
    SongRepository songReposit;

    @Test
    void testAddNewSong() {
    }
    
    @Test
    void testDeleteSong() {
        
    }
    
    @Test
    void testGetSongById() {
        
    }
    
    @Test
    void testGetSongByTitle() {
        
    }
    
    @Test
    void testGetSongs() {
        
    }
    
    @Test
    void testGetSongsByGenre() {
        
    }
    
    @Test
    void testSongsCount() {
        when(mongoTemplate.count(new Query(), Song.class)).thenReturn((long) 100);
        assertInstanceOf(long.class, songReposit.songsCount());
    }

    @Test
    void testUpdateSong() {

    }
}
