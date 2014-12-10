package com.home_didact.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.home_didact.R;


public class MainActivity extends Activity {



    int lastLearnerID;




     /*
    Determine the last learner active so we can provide a link to their profile
        and a link to create a new lesson for them.
    If no last learner (ie no existing learners), display only a link to add a learner?
     */
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



    /**
     *
     * Main nav button links
     *
     */

    /*
    Load the current learner profile
     */
    public void loadLearnerProfileActivity(View view) {
        Intent intent = new Intent(this, LearnerProfileActivity.class);
        startActivity(intent);
    }

    /*
    Load the new lesson activity
     */
    public void loadNewLessonActivity(View view) {
        Intent intent = new Intent(this, NewLessonActivity.class);
        startActivity(intent);
    }

    /*
    Load the change learner activity
    */
    public void loadChangeLearnerActivity(View view) {
        Intent intent = new Intent(this, ListLearnersActivity.class);
        startActivity(intent);
    }

    /*
    Load the new learner activity
     */
    public void loadNewLearnerActivity(View view) {
        Intent intent = new Intent(this, NewLearnerActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastLearnerID = getLastLearnerID();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
