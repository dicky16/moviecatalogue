package com.example.moviecatalogue.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.model.MovieModel;
import com.example.moviecatalogue.object.MovieSearchObject;
import com.example.moviecatalogue.service.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchMovieViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> listSearchMovie = new MutableLiveData<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    public void setListSearchMovie (String search) {
        Call<MovieSearchObject> searchObjectCall = serviceApi.getSearchMovie(search);
        searchObjectCall.enqueue(new Callback<MovieSearchObject>() {
            @Override
            public void onResponse(Call<MovieSearchObject> call, Response<MovieSearchObject> response) {
                if(response.isSuccessful()) {
                    listSearchMovie.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieSearchObject> call, Throwable t) {
                Log.w("Response Failure", t.getMessage());
            }
        });
    }

    public LiveData<List<MovieModel>> getListSearchMovie() {
        return listSearchMovie;
    }

}
