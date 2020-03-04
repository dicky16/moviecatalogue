package com.example.moviecatalogue.service;

import com.example.moviecatalogue.BuildConfig;
import com.example.moviecatalogue.model.MovieResponse;
import com.example.moviecatalogue.model.ResultsItem;
import com.example.moviecatalogue.object.MovieObject;
import com.example.moviecatalogue.object.MovieSearchObject;
import com.example.moviecatalogue.object.TvObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("discover/movie?api_key=" + BuildConfig.API_KEY)
    Call<MovieObject> getMovieList(@Query("id") int Id, @Query("language") String language);

    @GET("discover/tv?api_key=" + BuildConfig.API_KEY)
    Call<TvObject> getTvList (@Query("id") int Id, @Query("language") String language);

    @GET("search/movie?api_key=" + BuildConfig.API_KEY + "&language=en-US&query=")
    Call<MovieSearchObject> getSearchMovie (@Query("query") String search);

    @GET("search/tv?api_key=" + BuildConfig.API_KEY + "&language=en-US&query=")
    Call<TvObject> getSearchTv (@Query("query") String search);

    @GET("discover/movie?api_key="+ BuildConfig.API_KEY)
    Call<MovieResponse> getRelease (@Query("primary_release_date.gte") String dateGte,
                                    @Query("primary_release_date.lte") String dateLte);
//    Call<MovieResponse> getRelease();
}
