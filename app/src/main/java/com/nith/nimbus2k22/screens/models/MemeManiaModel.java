package com.nith.nimbus2k22.screens.models;

public class MemeManiaModel {
    String username;
    String caption;
    String userimage;
    String memeImage;
//    int likecount;

    public MemeManiaModel(String username, String caption, String userimage, String memeImage ) {
        this.username = username;
        this.caption = caption;
        this.userimage = userimage;
        this.memeImage = memeImage;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getMemeImage() {
        return memeImage;
    }

    public void setMemeImage(String memeImage) {
        this.memeImage = memeImage;
    }
}
