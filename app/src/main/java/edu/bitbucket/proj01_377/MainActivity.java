package edu.bitbucket.proj01_377;
/*   Asg 01 in CS377, Fall 2016
    The specs :
0. Have a splash screen where users can choose to view or add reviews.
1. Ability for user to add four fields of "review" info to a database
2. Ability for user to view all previously added reviews in a summary table form.
3. Ability for user to view a single review, accessed by clicking on a row in the summary table.
4. Ability for user to filter the review summary table by any one of the four fields.

   This app is a proof of concept for a universal judgement management tool to allow
   user to impulsively, and compulsively judge ANYTHING!!

   N.B. >>>>>>>>>>>>>>>>>
    * This is tested on a Nexus 6 API 23 emulator
    * Each onCreate of MainActivity adds 4 DB records with filter targets xx and xyz
    *     can be commented out below (around line 40)

  Proudly formatted in 1975 East German Retro styling

  PROTEST - the docs and tools for formatting displays are pitifully inadequate
        and so, not surprisingly, my formatting follows the tools, i.e. pitiful
     ?? Why not support CSS ??
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ReviewDBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new ReviewDBHelper(this);
        db = dbHelper.getWritableDatabase();

        //  >>>>>>> adding 4 or 5 startup reviews here
        initDb();
        // showReviews();
    }


    //  -------------------------------
    protected void startDoList(View v){
    /*  Filter on activity main?? or separate activity? (Fragment??)
    * */
        Intent intent = new Intent(this, DoList.class);

        // capture any Filter string for intent

        TextView tvFilter = (TextView) findViewById(R.id.editFilter);
        String filterTarget = tvFilter.getText().toString();
        tvFilter.setText("");
        intent.putExtra("filterExtra",filterTarget);
        startActivity(intent);
    }

    protected void startDoAdd(View v){
    /*
    * */
        Intent intent = new Intent(this, DoAdd.class);
        startActivity(intent);
    }
    //  -------------------------------
    @Override
    public void onPause() {
    /*
       Attempt to reset feedback and Filter target fields on Main
      */
        super.onPause();  // Always call the superclass method first
        setContentView(R.layout.activity_main);

        TextView txt = (TextView) findViewById(R.id.mainFeedback);
        txt.setText("...");
        TextView tvFilter = (TextView) findViewById(R.id.editFilter);
        tvFilter.setText("");
    }


    protected void initDb() {
    /*  Manually adds 4 startup reviews
    * */

        ContentValues values = new ContentValues();

        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT, "Sunrise xyz");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_RATING, " 2");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_DATE, "Aug 3, 2016");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW, "Mostly xyz Cloudy");
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ReviewSchema.ReviewColumns.TABLE_NAME, null, values);
        if (newRowId == -1) {
            TextView txt = (TextView) findViewById(R.id.mainFeedback);
            txt.setText("Add Failed");
            return;  // exit method
        } else {
            TextView txt = (TextView) findViewById(R.id.mainFeedback);
            txt.setText("Added Review");
        }

        // Insert the new row, returning the primary key value of the new row
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT, "Shoes xx");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_RATING, " -3");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_DATE, "Aug 13, 2016");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW, "Sole is xx Loose");
        newRowId = db.insert(ReviewSchema.ReviewColumns.TABLE_NAME, null, values);

        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT, "Coffee xyz");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_RATING, " 5");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_DATE, "Aug 23, 2016");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW, "Waking xyz Up");
        newRowId = db.insert(ReviewSchema.ReviewColumns.TABLE_NAME, null, values);

        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT, "Attitude xx");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_RATING, " 0");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_DATE, "Sept 3, 2016");
        values.put(ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW, "Meh xx ");
        newRowId = db.insert(ReviewSchema.ReviewColumns.TABLE_NAME, null, values);
    }



    // ---------------- Test that initDb worked -------------------
    public void showReviews(){
    /*  Dumps reviews to main Feedback field
    * */
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                ReviewSchema.ReviewColumns._ID,
                ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT,
                ReviewSchema.ReviewColumns.COLUMN_NAME_RATING,
                ReviewSchema.ReviewColumns.COLUMN_NAME_DATE,
                ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW
        };

        String sortOrder =
                ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT + " ASC";

        Cursor c = db.query(
                ReviewSchema.ReviewColumns.TABLE_NAME,           // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause *** ? FILTER
                null,                                     // The values for the WHERE clause *** ? FILTER
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        boolean more = c.moveToFirst();
        String result = "";
        while(more == true) {

            String event = c.getString(c.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT));
            String rating = c.getString(c.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_RATING));
            String date = c.getString(c.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_DATE));
            String comment = c.getString(c.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW));
            result += event +": " + rating + " - "+date+" (" + comment + ") \n";
            more = c.moveToNext();
        }

        ((TextView) findViewById(R.id.mainFeedback)).setText(result);

    }  // showReviews



}
