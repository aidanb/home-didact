package com.home_didact.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import com.home_didact.appobjects.Learner;
import com.home_didact.appobjects.Lesson;

import java.util.ArrayList;
import java.util.List;


public class LessonDBHandler extends SQLiteOpenHelper {

    int lastLearnerID;

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db";

    // table name
    private static final String TABLE_LESSONS = "lessons";

    // columns
    private static final String LEARNER_ID = "learner_id";
    private static final String LESSON_ID = "lesson_id";
    private static final String DATE = "date";
    private static final String DURATION = "duration"; // in minutes
    private static final String CATEGORIES = "categories";
    private static final String GOOD_NEWS = "good_news";
    private static final String COMMENTS = "comments";
    private static final String LOCATION = "location";



    public LessonDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // add a new lesson
    public void addLesson(Lesson lesson){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LEARNER_ID, getLastLearnerID(););
        values.put(DATE, lesson.getDate().toString());
        values.put(LOCATION, lesson.getLocation());

        db.insert(TABLE_LESSONS, null, values);
        db.close();
    }

    // START FROM HERE!

    // get a single lesson
    public Lesson getLesson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_LESSONS,
                new String[] {LEARNER_ID, NAME, ADDRESS, PHONE_NUMBER}, LEARNER_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor == null) {
            return null;
        }

        cursor.moveToFirst();
        Learner learner = new Learner(cursor.getString(1), cursor.getString(2), cursor.getString(3));

        cursor.close();
        db.close();
        return learner;
    }

    // get all learners
    public List<Learner> getLearners() {

        List<Learner> learnerList = new ArrayList<Learner>();
        String selectAll = "SELECT * FROM " + TABLE_LESSONS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor == null) {
            cursor.close();
            return null;
        }

        cursor.moveToFirst();
        try {
            while (cursor.moveToNext()) {
                Learner learner = new Learner(cursor.getString(1),
                        cursor.getString(2), cursor.getString(3));
                learnerList.add(learner);
            }
        } finally {
          cursor.close();
        }

        db.close();
        return learnerList;
    }

    //get learner count
    public int getLearnerCount() {

        String selectAll = "SELECT * FROM " + TABLE_LESSONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);

        int count = cursor.getCount();

        cursor.close();
        db.close();
        return count;
    }

    // update a learner
    public int updateLearner(Learner learner) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, learner.getName());
        values.put(ADDRESS, learner.getAddress());
        values.put(PHONE_NUMBER, learner.getPhoneNumber());

        int result = db.update(TABLE_LESSONS, values, LEARNER_ID + " =?",
                new String[] { String.valueOf(learner.getID())});

        db.close();
        return result;
    }

    // delete a learner
    public void deleteLearner(Learner learner) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LESSONS, LEARNER_ID + " =?",
                new String[] {String.valueOf(learner.getID())});
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LEARNERS_TABLE = "CREATE TABLE " + TABLE_LESSONS
                + "(" + LEARNER_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + PHONE_NUMBER + " TEXT," + ADDRESS + " TEXT" + ")";
        // drop table if it exists
        db.execSQL(CREATE_LEARNERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSONS);
        onCreate(db);
    }

    private int getLastLearnerID() {
        /*
        This value is set in one of two places:
            1. When the learner is changed using ListLearners.
            2. When the only existing learner is created.
         */

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        int defaultID = -1;
        int lastLearnerID = sharedPref.getInt("last_learner_ID", defaultID);
        return lastLearnerID;
    }


}
