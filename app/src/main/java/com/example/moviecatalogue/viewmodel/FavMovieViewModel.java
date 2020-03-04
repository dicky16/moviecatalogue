package com.example.moviecatalogue.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviecatalogue.model.FavoriteMovieModel;
import com.example.moviecatalogue.repository.FavoriteMovieRepository;

import java.util.List;

public class FavMovieViewModel extends AndroidViewModel {
    private FavoriteMovieRepository favoriteMovieRepository;
    private LiveData<List<FavoriteMovieModel>> allFavMovie;

    public FavMovieViewModel(@NonNull Application application) {
        super(application);
        favoriteMovieRepository = new FavoriteMovieRepository(application);
        allFavMovie = favoriteMovieRepository.getAllFavMovie();
    }

    public void insert(FavoriteMovieModel favoriteMovieModel) {
        favoriteMovieRepository.insert(favoriteMovieModel);
    }

    public void delete(FavoriteMovieModel favoriteMovieModel) {
        favoriteMovieRepository.delete(favoriteMovieModel);
    }

    public void deleteAllFavMovie() {
        favoriteMovieRepository.deleteAllFavMovie();
    }

    public LiveData<List<FavoriteMovieModel>> getAllFavMovie() {
        return allFavMovie;
    }


}
