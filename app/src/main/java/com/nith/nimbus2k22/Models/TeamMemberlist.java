package com.nith.nimbus2k22.Models;

public class TeamMemberlist {
    int id;
    String name;
    String team_name;
    String position;
    String image;

    public TeamMemberlist(int id, String name, String team_name, String position, String image) {
        this.id = id;
        this.name = name;
        this.team_name = team_name;
        this.position = position;
        this.image = image;
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

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    };
}
