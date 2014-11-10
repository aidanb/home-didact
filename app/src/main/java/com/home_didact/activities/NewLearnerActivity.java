package com.home_didact.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.home_didact.R;
import com.home_didact.appobjects.Learner;
import com.home_didact.database.LearnerDBHandler;

import java.util.List;

public class NewLearnerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_learner);

        LearnerDBHandler db = new LearnerDBHandler(this);

        // testing db ops
        Log.d("Insert: ", "Inserting ..");
        db.addLearner(new Learner("John", "123 Fake St", "95780654"));
        db.addLearner(new Learner("Peter", "123 Fake St", "95780654"));
        db.addLearner(new Learner("Mark", "123 Fake St", "95780654"));

        Log.d("Reading: ", "Reading all learners");
        List<Learner> learners = db.getLearners();

        for (Learner learner : learners) {
            String log = "ID: " + learner.getID() + ", name: " + learner.getName()
                    + ", address: " + learner.getAddress()
                    + ", phone: " + learner.getPhoneNumber();
            Log.d("Learner: ", log);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_learner, menu);
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
