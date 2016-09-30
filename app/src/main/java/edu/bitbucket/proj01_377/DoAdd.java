package edu.bitbucket.proj01_377;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Dale Bryant on 9/24/2016.
 */


public class DoAdd extends AppCompatActivity {
    /*

     */
    ReviewDBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbHelper = new ReviewDBHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        setContentView(R.layout.activity_add);

        TextView txt = (TextView) findViewById(R.id.add_feedback);
        txt.setText("...");
    }
    public void addReview(View view){
/*  From btnAddSubmit
    take strings from activity_add fields
    insert into db

 */
        ContentValues values = new ContentValues();

        String event = ((EditText) findViewById(R.id.detail_event)).getText().toString();
        String rating = ((EditText) findViewById(R.id.detail_rating)).getText().toString();
        String date = ((EditText) findViewById(R.id.add_date)).getText().toString();
        String review = ((EditText) findViewById(R.id.add_review)).getText().toString();

        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT, event);
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_RATING, rating);
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_DATE, date);
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW, review);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ReviewSchema.ReviewColumns.TABLE_NAME, null, values);
        if (newRowId == -1) {
            TextView txt = (TextView) findViewById(R.id.add_feedback);
            txt.setText("Add Failed");
            return;  // exit method
        } else {
            TextView txt = (TextView) findViewById(R.id.add_feedback);
            txt.setText("Added Grade");
        }
    } // addReview



}

