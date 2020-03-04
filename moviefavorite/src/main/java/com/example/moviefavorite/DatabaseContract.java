package com.example.moviefavorite;

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
//    public static final String AUTHORITY = "com.example.android.contentprovidersample.provider";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static String getString(Cursor cursor, String colum) {
        return cursor.getString(cursor.getColumnIndex(colum));
    }

    public static int getInt(Cursor cursor, String colum) {
        return cursor.getInt(cursor.getColumnIndex(colum));
    }

}
