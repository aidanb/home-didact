package com.home_didact.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aidan on 10/11/14.
 */



public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db";

    // table name
    private static final String TABLE_LEARNERS = "learners";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_LEARNERS_TABLE = "CREATE TABLE " + TABLE_LEARNERS

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
