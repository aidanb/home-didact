package com.home_didact.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.home_didact.R;
import com.home_didact.appobjects.Learner;
import com.home_didact.database.LearnerDBHandler;

import java.util.List;

public class LearnerProfileActivity extends Activity {

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
        setContentView(R.layout.activity_learner_profile);


        lastLearnerID = getLastLearnerID();


        LearnerDBHandler db = new LearnerDBHandler(this);
        Learner l = db.getLearner(lastLearnerID);

        // TODO: Add buttons so individual fields can be edited, or a button to go to an edit page.

        // TODO: this only displays the phone number

        TableLayout learnersTable;
        learnersTable = (TableLayout) findViewById(R.id.learners_table);

        TableRow trHeader = new TableRow(this);
        //trHeader.setId(10);
        trHeader.setBackgroundColor(Color.GRAY);
        trHeader.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        // name
        TextView labelName = new TextView(this);
        //labelName.setId(20);
        labelName.setText(l.getName());
        labelName.setTextColor(Color.WHITE);
        labelName.setTextSize(12);          //TODO: check size here
        labelName.setPadding(5, 5, 5, 5);
        trHeader.addView(labelName);

        // address
        TextView labelAddress = new TextView(this);
        //labelName.setId(20);
        labelName.setText(l.getAddress());
        labelName.setTextColor(Color.GRAY);
        labelName.setTextSize(12);          //TODO: check size here
        labelName.setPadding(5, 5, 5, 5);
        trHeader.addView(labelAddress);

        //phone number
        TextView labelPhoneNumber = new TextView(this);
        //labelName.setId(20);
        labelName.setText(l.getPhoneNumber());
        labelName.setTextColor(Color.WHITE);
        labelName.setTextSize(12);          //TODO: check size here
        labelName.setPadding(5, 5, 5, 5);
        trHeader.addView(labelPhoneNumber);

        learnersTable.addView(trHeader, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learner_profile, menu);
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
