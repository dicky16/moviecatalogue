package com.example.moviecatalogue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    @Expose
    private List<ResultsItem> MovieResults = null;

    public List<ResultsItem> getMovieResults() {
        return MovieResults;
    }

    public void setMovieResults(List<ResultsItem> movieResults) {
        MovieResults = movieResults;
    }

    public MovieResponse(List<ResultsItem> movieResults) {
        MovieResults = movieResults;
    }
}
