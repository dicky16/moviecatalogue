package com.example.moviecatalogue.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_favorite_movie")
public class FavoriteMovieModel implements Parcelable {

    @PrimaryKey
//    @ColumnInfo(name = "id")
    private int id;

//    @ColumnInfo(name = "title")
    private String title;

//    @ColumnInfo(name = "poster_path")
    private String poster_path;

//    @ColumnInfo(name = "overview")
    private String overview;

//    @ColumnInfo(name = "release_date")
    private String release_date;

//    public FavoriteMovieModel() {
//
//    }

    public FavoriteMovieModel(int id, String title, String poster_path, String overview, String release_date) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
    }

    protected FavoriteMovieModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
    }

    public static final Creator<FavoriteMovieModel> CREATOR = new Creator<FavoriteMovieModel>() {
        @Override
        public FavoriteMovieModel createFromParcel(Parcel source) {
            return new FavoriteMovieModel(source);
        }

        @Override
        public FavoriteMovieModel[] newArray(int size) {
            return new FavoriteMovieModel[size];
        }
    };

}
