package com.home_didact.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.home_didact.R;
import com.home_didact.appobjects.Learner;
import com.home_didact.database.LearnerDBHandler;

public class NewLearnerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_learner);

        //testLearnerDB();
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

    public void handleFormInput(View view) {

        // sanitise/check info
        EditText inputName = (EditText) findViewById(R.id.NewLearnerName);
        EditText inputAddress = (EditText) findViewById(R.id.NewLearnerAddress);
        EditText inputPhoneNumber = (EditText) findViewById(R.id.NewLearnerPhoneNumber);

        String name = inputName.getText().toString();
        String address = inputAddress.getText().toString();
        String phoneNumber = inputPhoneNumber.getText().toString();

        // submit info to database
        Learner newLearner = new Learner(name, address, phoneNumber);
        LearnerDBHandler db = new LearnerDBHandler(this);
        db.addLearner(newLearner);

        // set the last learner ID if this is the first learner in the DB
        if (db.getLearnerCount() == 1) {
            setLastLearnerID(newLearner.getID());
        }

        // now display conformation message and go to learner page
        // for now, go to list of learners
        Intent intent = new Intent(this, ListLearnersActivity.class);
        startActivity(intent);
    }


    /*
    Sets the last learner ID in shared preferences
    */
    private void setLastLearnerID(int learnerID) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("last_learner_ID", learnerID);
        editor.apply();
    }



    /*
    private void testLearnerDB() {
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

            Log.d("No. learners: " , "There are " + db.getLearnerCount() + "learners in the database");
        }
    }
    */
}
