package edu.bitbucket.proj01_377;

/**
 * Created by Dale Bryant on 9/24/2016.
 */
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowDetail extends AppCompatActivity {

    ReviewDBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        Intent intent = getIntent();
        int position = intent.getIntExtra("xposition",0);
        String queryWhere = intent.getStringExtra("xquerystring");
        //  also pass query string
        dbHelper = new ReviewDBHelper(this);
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "  + ReviewSchema.ReviewColumns.TABLE_NAME+" "+queryWhere,null);
        //Log.e("****** ","ShowDetail queryWhere:"+queryWhere);
        cursor.moveToPosition(position);

        String event = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_DATE));
        String rating = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_RATING));
        String review = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW));

        TextView tvEvent = (TextView) findViewById(R.id.detail_event);
        TextView tvRating = (TextView) findViewById(R.id.detail_rating);
        TextView tvDate = (TextView) findViewById(R.id.detail_date);
        TextView tvReview = (TextView) findViewById(R.id.detail_review);

        tvEvent.setText(event);
        tvDate.setText(date);
        tvRating.setText(rating);
        tvReview.setText(review);
    }
}
