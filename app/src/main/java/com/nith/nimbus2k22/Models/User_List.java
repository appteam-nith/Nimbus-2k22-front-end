package com.nith.nimbus2k22.Models;

public class User_List {
   String firebase;
   String username;
   String phone;
   String email;
   String name;
   boolean favTeamVote;
   int omegleReports;
   boolean omegleAllowed;
   String instaId;
   String profileImage;
   int totalScore;

    public User_List(String firebase, String username, String phone, String email, String name, boolean favTeamVote, int omegleReports, boolean omegleAllowed, String instaId, String profileImage, int totalScore) {
        this.firebase = firebase;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.favTeamVote = favTeamVote;
        this.omegleReports = omegleReports;
        this.omegleAllowed = omegleAllowed;
        this.instaId = instaId;
        this.profileImage = profileImage;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavTeamVote() {
        return favTeamVote;
    }

    public void setFavTeamVote(boolean favTeamVote) {
        this.favTeamVote = favTeamVote;
    }

    public int getOmegleReports() {
        return omegleReports;
    }

    public void setOmegleReports(int omegleReports) {
        this.omegleReports = omegleReports;
    }

    public boolean isOmegleAllowed() {
        return omegleAllowed;
    }

    public void setOmegleAllowed(boolean omegleAllowed) {
        this.omegleAllowed = omegleAllowed;
    }

    public String getInstaId() {
        return instaId;
    }

    public void setInstaId(String instaId) {
        this.instaId = instaId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
