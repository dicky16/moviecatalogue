package com.example.moviecatalogue.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviecatalogue.model.FavoriteTvModel;
import com.example.moviecatalogue.repository.FavoriteTvRepository;

import java.util.List;

public class FavTvViewModel extends AndroidViewModel {
    FavoriteTvRepository favoriteTvRepository;
    private LiveData<List<FavoriteTvModel>> allTvs;
    public FavTvViewModel(@NonNull Application application) {
        super(application);
        favoriteTvRepository = new FavoriteTvRepository(application);
        allTvs = favoriteTvRepository.getAllTvs();
    }

    public void insertTv(FavoriteTvModel favoriteTvModel) {
        favoriteTvRepository.insertTv(favoriteTvModel);
    }

    public void deleteTv(FavoriteTvModel favoriteTvModel) {
        favoriteTvRepository.deleteTv(favoriteTvModel);
    }

    public void deleteAllFavTv() {
        favoriteTvRepository.deleteAllFavTv();
    }

    public LiveData<List<FavoriteTvModel>> getAllFavTv() {
        return allTvs;
    }
}
