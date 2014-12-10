package com.home_didact.appobjects;

import java.util.Date;
import java.util.List;

public class Lesson {

    private Date date;
    private int duration;	// in minutes
    private List<String> categories;
    private String otherText;
    private String goodNewsStories;
    private String comments;
    private String location;

    public Lesson(Date date, String location) {
        this.date = date;
        this.location = location;
    }


    // GETTERS

    public Date getDate() {
        return this.date;
    }

    public String getLocation() {
        return this.location;
    }
}