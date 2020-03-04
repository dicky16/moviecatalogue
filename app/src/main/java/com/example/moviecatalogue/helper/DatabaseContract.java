package com.example.moviecatalogue.helper;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_NAME = "movie_provider";

    public static final class MovieColumns implements BaseColumns {
//        static String ID = "id";

        public static String TITLE = "title";

        public static String POSTER_PATH = "poster_path";

        public static String OVERVIEW = "overview";

        public static String DATE = "release_date";
    }

    public static final String AUTHORITY = "com.example.moviecatalogue";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

}
