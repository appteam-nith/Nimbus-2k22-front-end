package com.daisy.flappybird.domain;

public class FlappyBirdGame {
    int id;
    String userFirebaseId;
    int score;

    public FlappyBirdGame(int id, String userFirebaseId, int score) {
        this.id = id;
        this.userFirebaseId = userFirebaseId;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserFirebaseId() {
        return userFirebaseId;
    }

    public void setUserFirebaseId(String userFirebaseId) {
        this.userFirebaseId = userFirebaseId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
