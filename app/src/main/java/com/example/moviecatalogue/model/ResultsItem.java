package com.example.moviecatalogue.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogue.helper.DatabaseContract.MovieColumns.DATE;
import static com.example.moviecatalogue.helper.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.moviecatalogue.helper.DatabaseContract.MovieColumns.POSTER_PATH;
import static com.example.moviecatalogue.helper.DatabaseContract.MovieColumns.TITLE;
import static com.example.moviecatalogue.helper.DatabaseContract.getColumnInt;
import static com.example.moviecatalogue.helper.DatabaseContract.getColumnString;

public class ResultsItem {

    @SerializedName("overview")
    private String overview;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("id")
    private int id;

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ResultsItem() {
    }

    public ResultsItem(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, TITLE);
        this.posterPath = getColumnString(cursor, POSTER_PATH);
        this.releaseDate = getColumnString(cursor, DATE);
        this.overview = getColumnString(cursor, OVERVIEW);
    }

    @Override
    public String toString() {
        return
                "ResultsItem{" +
                        "overview = '" + overview + '\'' +
                        ",title = '" + title + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",release_date = '" + releaseDate + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}
