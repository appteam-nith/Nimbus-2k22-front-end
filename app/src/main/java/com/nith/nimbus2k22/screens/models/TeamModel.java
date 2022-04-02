package com.nith.nimbus2k22.screens.models;

public class TeamModel {
    int id;
    String Team_name;
    String Team_image;

    public TeamModel(int id, String team_name, String team_image) {
        this.id = id;
        Team_name = team_name;
        Team_image = team_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam_name() {
        return Team_name;
    }

    public void setTeam_name(String team_name) {
        Team_name = team_name;
    }

    public String getTeam_image() {
        return Team_image;
    }

    public void setTeam_image(String team_image) {
        Team_image = team_image;
    }
}
