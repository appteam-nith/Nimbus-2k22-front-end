package com.nith.nimbus2k22.Models;

public class CommentList {
    int id;
    String author;
    String text;
    String posted_on;

    public CommentList() {
    }

    public CommentList(int id, String author, String text, String posted_on) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.posted_on = posted_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPosted_on() {
        return posted_on;
    }

    public void setPosted_on(String posted_on) {
        this.posted_on = posted_on;
    }
}
