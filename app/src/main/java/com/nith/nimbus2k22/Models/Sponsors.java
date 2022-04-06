package com.nith.nimbus2k22.Models;

public class Sponsors {
    int id;
    String name;
    String description;
    String image;
    String website;
    int level;

    public Sponsors() {
    }

    public Sponsors(int id, String name, String description, String image, String website, int level) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.website = website;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

