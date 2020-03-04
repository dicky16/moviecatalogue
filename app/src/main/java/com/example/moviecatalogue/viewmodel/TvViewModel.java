package com.example.moviecatalogue.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.model.MovieModel;
import com.example.moviecatalogue.model.TvModel;
import com.example.moviecatalogue.object.TvObject;
import com.example.moviecatalogue.service.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvViewModel extends ViewModel {

    private MutableLiveData<List<TvModel>> listTv = new MutableLiveData<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    public void setLiveTvData(int page, String language) {
        Call<TvObject> tvObjectCall = serviceApi.getTvList(page, language);
        tvObjectCall.enqueue(new Callback<TvObject>() {
            @Override
            public void onResponse(Call<TvObject> call, Response<TvObject> response) {
                if (response.isSuccessful()) {
                    listTv.setValue(response.body().getResults());
                    Log.d("success", "ok");
                }
            }

            @Override
            public void onFailure(Call<TvObject> call, Throwable t) {
                Log.w("Response Failure", t.getMessage());
            }
        });
    }

    public LiveData<List<TvModel>> getListTv() {
        return listTv;
    }
}
