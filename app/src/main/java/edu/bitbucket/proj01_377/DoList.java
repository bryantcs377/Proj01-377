package edu.bitbucket.proj01_377;

/*
 * Created by Dale Bryant on 9/24/2016.
 */

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class DoList extends AppCompatActivity {
/*

 */

    ReviewDBHelper dbHelper;
    SQLiteDatabase db;
    String queryWhere = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String filterTarget = intent.getStringExtra("filterExtra");

        if (filterTarget.equals("")) {
            queryWhere = "";
        }else{
            queryWhere = "  WHERE " + ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW + "  LIKE \'%"+filterTarget+"%\'";
        }
        //Log.e("***** ","DoList queryWhere:"+queryWhere);
        dbHelper = new ReviewDBHelper(this);
        db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "  + ReviewSchema.ReviewColumns.TABLE_NAME+" "+queryWhere,null);

        ReviewCursorAdapter adapter = new ReviewCursorAdapter(this,c);

        ListView summarylistview = (ListView) findViewById(R.id.listReviews);

        summarylistview.setAdapter(adapter);

        summarylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //create intent
                Intent intent = new Intent(view.getContext(), ShowDetail.class);

                intent.putExtra("xposition", position);
                intent.putExtra("xquerystring",queryWhere);
                //start activity
                startActivity(intent);
            }


        });


    }
}