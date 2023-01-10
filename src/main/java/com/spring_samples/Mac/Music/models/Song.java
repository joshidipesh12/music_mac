package com.spring_samples.Mac.Music.models;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spring_samples.Mac.Music.utils.Formatters;

@Document("songs")
public class Song {
    @Id
    private String _id;
    
    private String id;
    private String title;
    private String genre;
    private int durInSec;
    private int likes;
    private int views;
    private String dateAdded;
    private String artistId;
    // public String art = "https://picsum.photos/seed/{id}/600"

    public Song(String title, String genre, int durInSec, String artistId) {
        super();
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.genre = genre;
        this.durInSec = durInSec;
        this.likes = 0;
        this.views = 0;
        this.dateAdded = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }

    public int getDurInSec() {
        return durInSec;
    }

    public String getTimeStamp() {
        return Formatters.timeFormatter(durInSec);
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // @Async
    @Override
    public String toString() {
        // Artist artist = getArtistById(artistId);
        return "Song: " + title + " by " + artistId + "\nReleased on: " + dateAdded + " Duration: " + getTimeStamp();
    }
}
