package edu.bitbucket.proj01_377;

import android.provider.BaseColumns;

/**
 * Created by Dale Bryant on 9/15/2016.
 */

public final class ReviewSchema {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ReviewSchema (){ /*constructor*/}
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ReviewColumns.TABLE_NAME + " (" +
                    ReviewColumns._ID + " INTEGER PRIMARY KEY," +
                    ReviewColumns.COLUMN_NAME_EVENT + TEXT_TYPE + COMMA_SEP +
                    ReviewColumns.COLUMN_NAME_RATING + TEXT_TYPE + COMMA_SEP +
                    ReviewColumns.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    ReviewColumns.COLUMN_NAME_REVIEW + TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ReviewColumns.TABLE_NAME;


    /* Inner class that defines the table contents */
    public static class ReviewColumns implements BaseColumns {
        public static final String TABLE_NAME = "reviewstable";

        public static final String COLUMN_NAME_EVENT="event";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_REVIEW = "review";
    }




}
