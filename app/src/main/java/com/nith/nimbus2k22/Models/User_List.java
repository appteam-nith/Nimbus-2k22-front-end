package com.nith.nimbus2k22.Models;

public class User_List {
    String firebase;
    String Username;
    String phone;
    String email;
    String firstname;
    String  lastname;
    int omegleReports;
    boolean omegleAllowed;
    String profileImage;


    public User_List(String s, String name, String phoneNumber, String emailAdd, String name1, String s1, int i, boolean b, String s2, boolean b1, String nith) {
    }

    public User_List(String firebase, String username, String phone, String email, String firstname, String lastname, int omegleReports, boolean omegleAllowed, String profileImage) {
        this.firebase = firebase;
        Username = username;
        this.phone = phone;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.omegleReports = omegleReports;
        this.omegleAllowed = omegleAllowed;
        this.profileImage = profileImage;

    }

    public String getFirebase() {
        return firebase;
    }

    public void setFirebase(String firebase) {
        this.firebase = firebase;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }


}
