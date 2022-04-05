package com.nith.nimbus2k22.Models;

public class TeamList {
    int id;
    String club_name;
    String image;

    public TeamList(int id, String club_name, String image) {
        this.id = id;
        this.club_name = club_name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
