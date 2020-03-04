package com.example.moviecatalogue.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_favorite_tv")
public class FavoriteTvModel implements Parcelable {

    @PrimaryKey
    private int id;

    private String title;

    private String poster_path;

    private String overview;

    private String release_date;

    public FavoriteTvModel(int id, String title, String poster_path, String overview, String release_date) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
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

    protected FavoriteTvModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
    }

    public static final Creator<FavoriteTvModel> CREATOR = new Creator<FavoriteTvModel>() {
        @Override
        public FavoriteTvModel createFromParcel(Parcel source) {
            return new FavoriteTvModel(source);
        }

        @Override
        public FavoriteTvModel[] newArray(int size) {
            return new FavoriteTvModel[size];
        }
    };


}
