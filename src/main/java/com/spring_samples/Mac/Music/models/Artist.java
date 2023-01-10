package com.spring_samples.Mac.Music.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("artists")
public class Artist {
    @Id
    public String _id;
    
    public String id;
    public String name;
    public String gender;
    public int followers;
    public String image;
    public String bio;
    public String url;
    public int age;

    public Artist(String name, String gender, String bio, String url, int age){
        super();
        this.id = UUID.randomUUID().toString();
        this.age = age;
        this.bio = bio;
        this.followers = 0;
        this.gender = gender;
        this.image = "";
        this.url = url;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getBio() {
        return bio;
    }

    public int getFollowers() {
        return followers;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Artist: " + name + ", Followers: " + followers + " Age: " + age;
    }
}
