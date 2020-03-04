package com.example.moviecatalogue.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.model.MovieModel;
import com.example.moviecatalogue.object.MovieObject;
import com.example.moviecatalogue.service.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<List<MovieModel>> listMovies = new MutableLiveData<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    public void setListMutableLiveData(int page, String language) {
        Call<MovieObject> movieObjectCall = serviceApi.getMovieList(page, language);
        movieObjectCall.enqueue(new Callback<MovieObject>() {
            @Override
            public void onResponse(Call<MovieObject> call, Response<MovieObject> response) {
                if (response.isSuccessful()) {
                    listMovies.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieObject> call, Throwable t) {
                Log.w("Response Failure", t.getMessage());
            }
        });
    }

    public LiveData<List<MovieModel>> getListMovies() {
        return listMovies;
    }
}
