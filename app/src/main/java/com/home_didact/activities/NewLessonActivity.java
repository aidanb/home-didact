package com.home_didact.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.home_didact.R;

public class NewLessonActivity extends Activity {


    int lastLearnerID;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lesson);

        lastLearnerID = getLastLearnerID();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_lesson, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
