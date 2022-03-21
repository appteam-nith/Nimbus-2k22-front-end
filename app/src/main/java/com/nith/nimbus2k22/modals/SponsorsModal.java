package com.nith.nimbus2k22.modals;

public class SponsorsModal {

    private String name;
    private String link;
    private String image;
    private String position;
    private int priority;

    public SponsorsModal(String name, String link, String image, String position, int priority) {
        this.name = name;
        this.link = link;
        this.image = image;
        this.position = position;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
