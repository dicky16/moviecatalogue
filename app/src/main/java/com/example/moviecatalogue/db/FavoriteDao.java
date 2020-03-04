package com.example.moviecatalogue.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.moviecatalogue.model.FavoriteMovieModel;
import com.example.moviecatalogue.model.FavoriteTvModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FavoriteDao {
    //movie
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavoriteMovie(FavoriteMovieModel favoriteMovieModel);

    @Query("SELECT * FROM tb_favorite_movie ORDER BY id DESC")
    LiveData<List<FavoriteMovieModel>> getAllMovies();

    //fav widget
    @Query("SELECT * FROM tb_favorite_movie")
    List<FavoriteMovieModel> selectAll();

    @Delete
    void delete(FavoriteMovieModel favoriteMovieModel);

    @Query("DELETE FROM tb_favorite_movie")
    void deleteAllFavMovie();
    //tv
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavoriteTv(FavoriteTvModel favoriteTvModel);

    @Query("SELECT * FROM tb_favorite_tv ORDER BY id DESC")
    LiveData<List<FavoriteTvModel>> getAllTvs();

    @Delete
    void deleteTv(FavoriteTvModel favoriteTvModel);

    @Query("DELETE FROM tb_favorite_tv")
    void deleteAllFavTv();
}
