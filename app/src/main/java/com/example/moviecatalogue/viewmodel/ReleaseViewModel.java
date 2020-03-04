package com.example.moviecatalogue.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.model.ResultsItem;
import com.example.moviecatalogue.object.ReleaseObject;
import com.example.moviecatalogue.service.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReleaseViewModel extends ViewModel {

    private List<ResultsItem> listRelease = new ArrayList<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ServiceApi serviceApi = retrofit.create(ServiceApi.class);
    //coba
//    public void setListRelease(String gte, String lte) {
//        Call<ReleaseObject> releaseObjectCall = serviceApi.getRelease(gte, lte);
//        releaseObjectCall.enqueue(new Callback<ReleaseObject>() {
//            @Override
//            public void onResponse(Call<ReleaseObject> call, Response<ReleaseObject> response) {
//                if (response.isSuccessful()) {
//                    listRelease.addAll(response.body().getResults());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ReleaseObject> call, Throwable t) {
//                Log.w("response gagal", t.getMessage());
//            }
//        });
//    }
//
//    public List<ResultsItem> getListRelease() {
//        return listRelease;
//    }
}
