package edu.bitbucket.proj01_377;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Dale Bryant on 9/28/2016.
 */

public class ReviewCursorAdapter extends CursorAdapter {
    /*
        DB cursor for review summary
     */
    public ReviewCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.show_review_summary, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvEvent = (TextView) view.findViewById(R.id.summary_event);
        TextView tvRating = (TextView) view.findViewById(R.id.summary_rating);
//        TextView tvDate = (TextView) view.findViewById(R.id.detail_date);
//        TextView tvReview = (TextView) view.findViewById(R.id.detail_review);
        // Extract properties from cursor
        String event = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_EVENT));
        String rating = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_RATING));
//        String review = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_REVIEW));
//        String date = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.ReviewColumns.COLUMN_NAME_DATE));

        // Populate fields with extracted properties
        tvEvent.setText(event);
        tvRating.setText(rating);
//        tvDate.setText(date);
//        tvReview.setText(review);
    }
}
