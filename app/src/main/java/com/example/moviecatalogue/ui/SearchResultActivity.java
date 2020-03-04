package com.example.moviecatalogue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.MovieAdapter;
import com.example.moviecatalogue.adapter.TvAdapter;
import com.example.moviecatalogue.model.MovieModel;
import com.example.moviecatalogue.model.TvModel;
import com.example.moviecatalogue.viewmodel.SearchMovieViewModel;
import com.example.moviecatalogue.viewmodel.SearchTvViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchResultActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private MovieAdapter movieAdapter;
    private TvAdapter tvAdapter;
    private String query;

    //search view
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public static final String SEARCH_QUERY = "query";
    public static final String FROM = "from";

    //movie
    private Observer<List<MovieModel>> getMovies = new Observer<List<MovieModel>>() {
        @Override
        public void onChanged(List<MovieModel> movieModels) {
            if (movieModels != null) {
                movieAdapter.setMovieData((ArrayList<MovieModel>) movieModels);
                showLoading(false);
            }
        }
    };

    //tv
    //movie
    private Observer<List<TvModel>> getTvs = new Observer<List<TvModel>>() {
        @Override
        public void onChanged(List<TvModel> movieModels) {
            if (movieModels != null) {
                tvAdapter.setTvData((ArrayList<TvModel>) movieModels);
                showLoading(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie);
        //init adapter recycler
        RecyclerView recyclerView = findViewById(R.id.rv_movie);

        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();
        tvAdapter = new TvAdapter();
        tvAdapter.notifyDataSetChanged();
        progressBar = findViewById(R.id.pb_loading);

        query = getIntent().getExtras().getString(SEARCH_QUERY);
        String from = getIntent().getExtras().getString(FROM);
        if (from.equals("movie")) {
            //view model
            SearchMovieViewModel searchMovieViewModel = ViewModelProviders.of(Objects.requireNonNull(this)).get(SearchMovieViewModel.class);
            searchMovieViewModel.getListSearchMovie().observe(this,getMovies);
            //get query
            searchMovieViewModel.setListSearchMovie(query);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(movieAdapter);

            movieAdapter.OnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
                @Override
                public void onItemClicked(MovieModel movieModel) {
                    Intent intent = new Intent(SearchResultActivity.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.KEY_EXTRA, movieModel);
                    Log.d("id", "movie_id : " +movieModel.getId());
                    startActivity(intent);
                }
            });
        } else {
            SearchTvViewModel searchTvViewModel = ViewModelProviders.of(this).get(SearchTvViewModel.class);
            searchTvViewModel.getListMovies().observe(this, getTvs);
            searchTvViewModel.setListMutableLiveData(query);

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(tvAdapter);

            tvAdapter.OnItemClickCallback(new TvAdapter.OnItemClickCallback() {
                @Override
                public void onItemClicked(TvModel tvModel) {
                    Intent intent = new Intent(SearchResultActivity.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.KEY_EXTRA_TV, tvModel);
                    Log.d("id", "tv_id : " +tvModel.getId());
                    startActivity(intent);
                }
            });
        }
        showLoading(true);

        //back
        String result = getResources().getString(R.string.search_result) + query;
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(result);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        MenuItem searchItem = menu.findItem(R.id.search_menu);
//        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//            searchItem.expandActionView();
//        }
//
//        if (searchView != null) {
////            searchView.setQueryHint(getResources().getString(R.string.search_movie));
//            searchView.setQuery(query, true);
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
//            queryTextListener = new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextChange(String newText) {
//
//
//                    return true;
//                }
//                @Override
//                public boolean onQueryTextSubmit(String query) {
////                    SearchMovieViewModel searchMovieViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SearchMovieViewModel.class);
////                    searchMovieViewModel.getListSearchMovie().observe(getViewLifecycleOwner(),getMovies);
////                    searchMovieViewModel.setListSearchMovie(query);
////                    showLoading(true);
////                    Intent i = new Intent(this, SearchResultActivity.class);
////                    i.putExtra(SEARCH_QUERY, query);
////                    getActivity().startActivity(i);
//                    return false;
//                }
//            };
//            searchView.setOnQueryTextListener(queryTextListener);
//        }
//        if (searchView == null){
//            this.onBackPressed();
//        }
//
//        return true;
//    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
