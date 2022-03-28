package com.nith.nimbus2k22.Models;

public class Events_List {
String title;
String description;
String startTime;
String endTime;
String clubName;
String platform;
String image;
String regUrl;
int type;

    public Events_List() {
    }

    public Events_List(String title, String description, String startTime, String endTime, String clubName, String platform, String image, String regUrl, int type) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.clubName = clubName;
        this.platform = platform;
        this.image = image;
        this.regUrl = regUrl;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegUrl() {
        return regUrl;
    }

    public void setRegUrl(String regUrl) {
        this.regUrl = regUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
