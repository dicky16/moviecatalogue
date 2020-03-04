package com.example.moviecatalogue.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.MovieAdapter;
import com.example.moviecatalogue.model.MovieModel;
import com.example.moviecatalogue.ui.DetailActivity;
import com.example.moviecatalogue.ui.SearchResultActivity;
import com.example.moviecatalogue.ui.SettingActivity;
import com.example.moviecatalogue.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.moviecatalogue.ui.SearchResultActivity.FROM;
import static com.example.moviecatalogue.ui.SearchResultActivity.SEARCH_QUERY;

public class MovieFragment extends Fragment {

    //search view
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
//    private String mSearchQuery = "";
//    private final String SEARCH_QUERY = "searchQuery";
    //inital
    private ProgressBar progressBar;

    private MovieAdapter movieAdapter;

    private Observer<List<MovieModel>> getMovies = new Observer<List<MovieModel>>() {
    @Override
    public void onChanged(List<MovieModel> movieModels) {
        if (movieModels != null) {
            movieAdapter.setMovieData((ArrayList<MovieModel>) movieModels);
            showLoading(false);
        }
    }
    };
    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//        if (savedInstanceState != null) {
//            mSearchQuery = savedInstanceState.getString("searchQuery");
//        }
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        if (!mSearchQuery.isEmpty()) {
//            outState.putString(SEARCH_QUERY, mSearchQuery);
//        }
//        super.onSaveInstanceState(outState);
//        mSearchQuery = searchView.getQuery().toString();
//        outState.putString("searchQuery", mSearchQuery);
//    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.pb_loading);

            MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
            movieViewModel.getListMovies().observe(getViewLifecycleOwner(), getMovies);
            movieViewModel.setListMutableLiveData(1, getString(R.string.language));


            movieAdapter = new MovieAdapter();
            movieAdapter.notifyDataSetChanged();
            progressBar = view.findViewById(R.id.pb_loading);

            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(movieAdapter);
            movieAdapter.OnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MovieModel movieModel) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_EXTRA, movieModel);
                Log.d("id", "movie_id : " +movieModel.getId());
                startActivity(intent);
            }
        });

            showLoading(true);
        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setQueryHint(getResources().getString(R.string.search_movie));
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {


                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
//                    SearchMovieViewModel searchMovieViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SearchMovieViewModel.class);
//                    searchMovieViewModel.getListSearchMovie().observe(getViewLifecycleOwner(),getMovies);
//                    searchMovieViewModel.setListSearchMovie(query);
//                    showLoading(true);
                    String movieFragment = "movie";
                    Intent i = new Intent(getActivity(), SearchResultActivity.class);
                    i.putExtra(SEARCH_QUERY, query);
                    i.putExtra(FROM, movieFragment);
                    getActivity().startActivity(i);
                    return false;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            Intent i = new Intent(getActivity(), SettingActivity.class);
            startActivity(i);
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);

    }


}
