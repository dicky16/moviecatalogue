package com.example.moviefavorite;

import android.net.Uri;

public class DatabaseContract {

    public static final String TABLE_NAME = "tb_favorite_movie";
    public static final String AUTHORITY = "com.example.moviecatalogue.prov";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "overview";
    public static final String COLUMN_POSTER = "poster_path";

}
