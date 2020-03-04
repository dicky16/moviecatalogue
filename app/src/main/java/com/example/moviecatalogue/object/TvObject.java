package com.example.moviecatalogue.object;

import com.example.moviecatalogue.model.MovieModel;
import com.example.moviecatalogue.model.TvModel;

import java.util.List;

public class TvObject {
    private List<TvModel> results;

    public List<TvModel> getResults() {
        return results;
    }

    public void setResults(List<TvModel> results){
        this.results = results;
    }

}
