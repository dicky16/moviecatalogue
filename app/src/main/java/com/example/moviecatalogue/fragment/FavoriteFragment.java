package com.example.moviecatalogue.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.ui.SettingActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnMovieFav = view.findViewById(R.id.btn_movie_fav);
        Button btnTvFav = view.findViewById(R.id.btn_tv_fav);

        final FavoriteTvFragment favoriteTvFragment = new FavoriteTvFragment();
        final FavoriteMovieFragment favoriteMovieFragment = new FavoriteMovieFragment();
        //check active fragment
        if (favoriteMovieFragment.isVisible()){
            btnMovieFav.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnTvFav.setBackgroundColor(getResources().getColor(R.color.white));
        } else if(favoriteTvFragment.isVisible()){
            btnMovieFav.setBackgroundColor(getResources().getColor(R.color.white));
            btnTvFav.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        setFragmentMovie(favoriteMovieFragment);
        btnTvFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentTv(favoriteTvFragment);
            }
        });
        btnMovieFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentMovie(favoriteMovieFragment);
            }
        });

    }
    private void setFragmentMovie(Fragment fragmentMovie){
//        FavoriteMovieFragment favoriteFragment = new FavoriteMovieFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_fav, fragmentMovie)
                .addToBackStack(null)
                .commit();
    }

    private void setFragmentTv(Fragment fragmentTv) {
//        FavoriteMovieFragment favoriteFragment = new FavoriteMovieFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_fav, fragmentTv)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_in_fav) {
            Intent i = new Intent(getActivity(), SettingActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
