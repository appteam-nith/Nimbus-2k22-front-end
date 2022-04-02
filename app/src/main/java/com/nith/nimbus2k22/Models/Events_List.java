package com.nith.nimbus2k22.Models;

public class Events_List {
int id;
String name;
String abtract;
String info;
String venue;
String start;
String end;
String image;
String regURL;
int type;
String department;

    public Events_List() {
    }

    public Events_List(int id, String name, String abtract, String info, String venue, String start, String end, String image, String regURL, int type, String department) {
        this.id = id;
        this.name = name;
        this.abtract = abtract;
        this.info = info;
        this.venue = venue;
        this.start = start;
        this.end = end;
        this.image = image;
        this.regURL = regURL;
        this.type = type;
        this.department = department;
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

    public String getAbtract() {
        return abtract;
    }

    public void setAbtract(String abtract) {
        this.abtract = abtract;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegURL() {
        return regURL;
    }

    public void setRegURL(String regURL) {
        this.regURL = regURL;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
