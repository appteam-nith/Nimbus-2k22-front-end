package com.daisy.flappybird.domain;

public class FlappyBirdScore {
    String firebase;
    String username;
    String name;
    int totalScore;

    public FlappyBirdScore(String firebase, String username, String name, int totalScore) {
        this.firebase = firebase;
        this.username = username;
        this.name = name;
        this.totalScore = totalScore;
    }

    public String getFirebase() {
        return firebase;
    }

    public void setFirebase(String firebase) {
        this.firebase = firebase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}

