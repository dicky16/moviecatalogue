package com.example.moviecatalogue.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moviecatalogue.db.FavoriteDao;
import com.example.moviecatalogue.db.FavoriteDatabase;
import com.example.moviecatalogue.model.FavoriteTvModel;

import java.util.List;

public class FavoriteTvRepository {

    private FavoriteDao favoriteDao;
    private LiveData<List<FavoriteTvModel>> allFavTvData;

    public FavoriteTvRepository (Application application) {
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        favoriteDao = database.favoriteDao();
        allFavTvData = favoriteDao.getAllTvs();
    }

    public LiveData<List<FavoriteTvModel>> getAllTvs(){
        return allFavTvData;
    }

    public void insertTv(FavoriteTvModel favoriteTvModel) {
        new InsertFavTvAsyncTask(favoriteDao).execute(favoriteTvModel);
    }

    public void deleteTv(FavoriteTvModel favoriteTvModel) {
        new DeleteFavTvAsyncTask(favoriteDao).execute(favoriteTvModel);
    }

    public void deleteAllFavTv() {
        new DeleteAllFavTvAsyncTask(favoriteDao).execute();
    }

    private static class InsertFavTvAsyncTask extends AsyncTask<FavoriteTvModel, Void, Void> {
        private FavoriteDao favoriteDao;

        private InsertFavTvAsyncTask(FavoriteDao favoriteDao){
            this.favoriteDao =  favoriteDao;
        }

        @Override
        protected Void doInBackground(FavoriteTvModel... favoriteTvModels) {
            favoriteDao.insertFavoriteTv(favoriteTvModels[0]);
            return null;
        }
    }

    private static class DeleteFavTvAsyncTask extends AsyncTask<FavoriteTvModel, Void, Void> {
        private FavoriteDao favoriteDao;

        private DeleteFavTvAsyncTask(FavoriteDao favoriteDao){
            this.favoriteDao =  favoriteDao;
        }

        @Override
        protected Void doInBackground(FavoriteTvModel... favoriteTvModels) {
            favoriteDao.deleteTv(favoriteTvModels[0]);
            return null;
        }
    }

    private static class DeleteAllFavTvAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavoriteDao favoriteDao;

        private DeleteAllFavTvAsyncTask(FavoriteDao favoriteDao){
            this.favoriteDao =  favoriteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteDao.deleteAllFavTv();
            return null;
        }
    }


}
