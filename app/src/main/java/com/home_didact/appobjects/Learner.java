package com.home_didact.appobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Learner {

    private String name;
    private String address;
    private String phoneNumber;
    private List<Lesson> lessons;
    //private photo	//TODO: support photos
    private Date firstMeeting;
    private Date lastMeeting;
    private int ID;
    private static int currID = 0;


    public Learner(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.lessons = new ArrayList<Lesson>();
        this.ID = currID;
        currID++;
    }


    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getID() {
        return this.ID;
    }

    public List<Lesson> getLessons() {
        return this.lessons;
    }

}
