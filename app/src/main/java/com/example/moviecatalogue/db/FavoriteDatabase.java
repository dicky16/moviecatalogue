package com.example.moviecatalogue.db;

import android.content.Context;
import android.net.Uri;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviecatalogue.model.FavoriteMovieModel;
import com.example.moviecatalogue.model.FavoriteTvModel;

@Database(entities = {FavoriteMovieModel.class, FavoriteTvModel.class}, version = 2, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {
    private static FavoriteDatabase instance;
//    public static FavoriteDao favoriteDao();
    public abstract FavoriteDao favoriteDao();

    public static synchronized FavoriteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class, "favorite_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
