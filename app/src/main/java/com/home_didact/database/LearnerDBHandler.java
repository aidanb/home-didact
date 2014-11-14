package com.home_didact.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.home_didact.appobjects.Learner;

import java.util.ArrayList;
import java.util.List;


public class LearnerDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db";

    // table name
    private static final String TABLE_LEARNERS = "learners";

    // columns
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phone_number";


    public LearnerDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // add a new learner
    public void addLearner(Learner learner){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, learner.getName());
        values.put(PHONE_NUMBER, learner.getPhoneNumber());
        values.put(ADDRESS, learner.getAddress());

        db.insert(TABLE_LEARNERS, null, values);
        db.close();
    }

    // get a single learner
    public Learner getLearner(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LEARNERS,
                new String[] {ID, NAME, ADDRESS, PHONE_NUMBER}, ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor == null) {
            return null;
        }

        cursor.moveToFirst();
        Learner learner = new Learner(cursor.getString(0), cursor.getString(1), cursor.getString(2));

        cursor.close();
        db.close();
        return learner;
    }

    // get all learners
    public List<Learner> getLearners() {

        List<Learner> learnerList = new ArrayList<Learner>();
        String selectAll = "SELECT * FROM " + TABLE_LEARNERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor == null) {
            cursor.close();
            return null;
        }

        cursor.moveToFirst();
        try {
            while (cursor.moveToNext()) {
                Learner learner = new Learner(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2));
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

        String selectAll = "SELECT * FROM " + TABLE_LEARNERS;
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

        int result = db.update(TABLE_LEARNERS, values, ID + " =?",
                new String[] { String.valueOf(learner.getID())});

        db.close();
        return result;
    }

    // delete a learner
    public void deleteLearner(Learner learner) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LEARNERS, ID + " =?",
                new String[] {String.valueOf(learner.getID())});
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LEARNERS_TABLE = "CREATE TABLE " + TABLE_LEARNERS
                + "(" + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + PHONE_NUMBER + " TEXT," + ADDRESS + " TEXT" + ")";
        // drop table if it exists
        db.execSQL(CREATE_LEARNERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEARNERS);
        onCreate(db);
    }
}
