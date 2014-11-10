package com.home_didact.appobjects;

import java.util.Date;
import java.util.List;

public class Lesson {

    private Date date;
    //private something duration;	//TODO: duration type
    private List<String> categories;
    private String otherText;
    private String goodNewsStories;
    private String comments;
    private int sequenceNum;
    private static int curSeqNum = 0;
    private String location;

    public Lesson(Date date, String location) {
        this.date = date;
        this.location = location;
        this.sequenceNum = curSeqNum;
        curSeqNum++;
    }


    // GETTERS
    public int getSequenceNum() {
        return this.sequenceNum;
    }

    public Date getDate() {
        return this.date;
    }

    public String getLocation() {
        return this.location;
    }
}