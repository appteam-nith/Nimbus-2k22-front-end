package com.nith.nimbus2k22.screens.models;

public class CommentModel {
  String username;
  String usernameComment;
  String comment;
  String new_comment;
  String userimage;
    String userimage_comment;

    public CommentModel(String username, String usernameComment, String comment, String new_comment, String userimage, String userimage_comment) {
        this.username = username;
        this.usernameComment = usernameComment;
        this.comment = comment;
        this.new_comment = new_comment;
        this.userimage = userimage;
        this.userimage_comment = userimage_comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameComment() {
        return usernameComment;
    }

    public void setUsernameComment(String usernameComment) {
        this.usernameComment = usernameComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNew_comment() {
        return new_comment;
    }

    public void setNew_comment(String new_comment) {
        this.new_comment = new_comment;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getUserimage_comment() {
        return userimage_comment;
    }

    public void setUserimage_comment(String userimage_comment) {
        this.userimage_comment = userimage_comment;
    }
}
