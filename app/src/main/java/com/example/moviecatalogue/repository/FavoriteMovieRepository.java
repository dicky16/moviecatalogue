package com.example.moviecatalogue.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moviecatalogue.db.FavoriteDao;
import com.example.moviecatalogue.db.FavoriteDatabase;
import com.example.moviecatalogue.model.FavoriteMovieModel;
import com.example.moviecatalogue.model.FavoriteTvModel;

import java.util.List;

public class FavoriteMovieRepository {
    private FavoriteDao favoriteDao;
    private LiveData<List<FavoriteMovieModel>> allFavMovie;
    private LiveData<List<FavoriteTvModel>> allFavTvs;

    public FavoriteMovieRepository(Application application) {
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        favoriteDao = database.favoriteDao();
        allFavMovie = favoriteDao.getAllMovies();
//        allFavTvs = favoriteDao.getAllTvs();
    }

    public void insert(FavoriteMovieModel favoriteMovieModel) {
        new InsertFavMovieAsyncTask(favoriteDao).execute(favoriteMovieModel);
    }

    public void delete(FavoriteMovieModel favoriteMovieModel) {
        new DeleteFavMovieAsyncTask(favoriteDao).execute(favoriteMovieModel);
    }

    public void deleteAllFavMovie() {
        new DeleteAllFavMovieAsyncTask(favoriteDao).execute();
    }

    //movie
    public LiveData<List<FavoriteMovieModel>> getAllFavMovie() {
        return allFavMovie;
    }

    //tv
//    public LiveData<List<FavoriteTvModel>> getAllTvs(){
//        return allFavTvs;
//    }

    private static class InsertFavMovieAsyncTask extends AsyncTask<FavoriteMovieModel, Void, Void> {
        private FavoriteDao favoriteDao;

        private InsertFavMovieAsyncTask(FavoriteDao favoriteDao){
            this.favoriteDao =  favoriteDao;
        }

        @Override
        protected Void doInBackground(FavoriteMovieModel... favoriteMovieModels) {
            favoriteDao.insertFavoriteMovie(favoriteMovieModels[0]);
            return null;
        }
    }

    private static class DeleteFavMovieAsyncTask extends AsyncTask<FavoriteMovieModel, Void, Void> {
        private FavoriteDao favoriteDao;

        private DeleteFavMovieAsyncTask(FavoriteDao favoriteDao){
            this.favoriteDao =  favoriteDao;
        }

        @Override
        protected Void doInBackground(FavoriteMovieModel... favoriteMovieModels) {
            favoriteDao.delete(favoriteMovieModels[0]);
            return null;
        }
    }

    private static class DeleteAllFavMovieAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavoriteDao favoriteDao;

        private DeleteAllFavMovieAsyncTask(FavoriteDao favoriteDao){
            this.favoriteDao =  favoriteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteDao.deleteAllFavMovie();
            return null;
        }
    }

    //for tv
//    public void insertTv(FavoriteTvModel favoriteTvModel) {
//        new InsertFavTvAsyncTask(favoriteDao).execute(favoriteTvModel);
//    }
//
//    public void deleteTv(FavoriteTvModel favoriteTvModel) {
//        new DeleteFavTvAsyncTask(favoriteDao).execute(favoriteTvModel);
//    }
//
//    public void deleteAllFavTv() {
//        new DeleteAllFavTvAsyncTask(favoriteDao).execute();
//    }
//
//    private static class InsertFavTvAsyncTask extends AsyncTask<FavoriteTvModel, Void, Void> {
//        private FavoriteDao favoriteDao;
//
//        private InsertFavTvAsyncTask(FavoriteDao favoriteDao){
//            this.favoriteDao =  favoriteDao;
//        }
//
//        @Override
//        protected Void doInBackground(FavoriteTvModel... favoriteTvModels) {
//            favoriteDao.insertFavoriteTv(favoriteTvModels[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteFavTvAsyncTask extends AsyncTask<FavoriteTvModel, Void, Void> {
//        private FavoriteDao favoriteDao;
//
//        private DeleteFavTvAsyncTask(FavoriteDao favoriteDao){
//            this.favoriteDao =  favoriteDao;
//        }
//
//        @Override
//        protected Void doInBackground(FavoriteTvModel... favoriteTvModels) {
//            favoriteDao.deleteTv(favoriteTvModels[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteAllFavTvAsyncTask extends AsyncTask<Void, Void, Void> {
//        private FavoriteDao favoriteDao;
//
//        private DeleteAllFavTvAsyncTask(FavoriteDao favoriteDao){
//            this.favoriteDao =  favoriteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            favoriteDao.deleteAllFavTv();
//            return null;
//        }
//    }
}
