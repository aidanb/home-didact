package com.home_didact.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.home_didact.R;
import com.home_didact.appobjects.Learner;
import com.home_didact.database.LearnerDBHandler;

import java.util.List;

public class ListLearners extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_learners);

        // Load db rows and list all learners
        LearnerDBHandler db = new LearnerDBHandler(this);


        TableLayout learnersTable;
        learnersTable = (TableLayout) findViewById(R.id.learners_table);

        TableRow trHeader = new TableRow(this);
        //trHeader.setId(10);
        trHeader.setBackgroundColor(Color.GRAY);
        trHeader.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        // just display their name for now.
        TextView labelName = new TextView(this);
        //labelName.setId(20);
        labelName.setText("Learner");
        labelName.setTextColor(Color.WHITE);
        labelName.setPadding(5, 5, 5, 5);
        trHeader.addView(labelName);

        learnersTable.addView(trHeader, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                    ViewGroup.LayoutParams.WRAP_CONTENT));

        List<Learner> learners = db.getLearners();
        int count = 0;
        for (Learner l : learners) {
            Log.d("Retrieved: ", l.getName() + ", " + l.getAddress() + ", " + l.getPhoneNumber() + ", " + l.getID());

            TableRow tr = new TableRow(this);
            if(count%2!=0) tr.setBackgroundColor(Color.GRAY);
            tr.setId(100+count);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            TextView learnerName = new TextView(this);
            learnerName.setText(l.getName());
            learnerName.setTextColor(Color.WHITE);
            learnerName.setPadding(2,0,5,0);
            tr.addView(learnerName);

            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), LearnerProfile.class);
                    startActivity(intent);
                }
            });

            learnersTable.addView(tr, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            count++;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_learners, menu);
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
