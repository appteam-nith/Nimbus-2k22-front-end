package com.nith.nimbus2k22.Models;

public class Memes {
    String id;
    UserSerializerForMemes autohr;
    String photo;
    String text;
    String location;
    String posted_on;
    String number_of_likes;
    String number_of_comments;

    public Memes(String id, UserSerializerForMemes autohr, String photo, String text, String location, String posted_on, String number_of_likes, String number_of_comments) {
        this.id = id;
        this.autohr = autohr;
        this.photo = photo;
        this.text = text;
        this.location = location;
        this.posted_on = posted_on;
        this.number_of_likes = number_of_likes;
        this.number_of_comments = number_of_comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserSerializerForMemes getAutohr() {
        return autohr;
    }

    public void setAutohr(UserSerializerForMemes autohr) {
        this.autohr = autohr;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPosted_on() {
        return posted_on;
    }

    public void setPosted_on(String posted_on) {
        this.posted_on = posted_on;
    }

    public String getNumber_of_likes() {
        return number_of_likes;
    }

    public void setNumber_of_likes(String number_of_likes) {
        this.number_of_likes = number_of_likes;
    }

    public String getNumber_of_comments() {
        return number_of_comments;
    }

    public void setNumber_of_comments(String number_of_comments) {
        this.number_of_comments = number_of_comments;
    }
}
