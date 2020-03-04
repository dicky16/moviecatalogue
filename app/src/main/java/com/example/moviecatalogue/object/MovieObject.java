package com.example.moviecatalogue.object;

import com.example.moviecatalogue.model.MovieModel;

import java.util.List;

public class MovieObject {
    private List<MovieModel> results;

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }
}
