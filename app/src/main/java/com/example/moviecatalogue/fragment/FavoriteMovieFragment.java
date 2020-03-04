package com.example.moviecatalogue.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.FavoriteMovieAdapter;
import com.example.moviecatalogue.model.FavoriteMovieModel;
import com.example.moviecatalogue.ui.DetailActivity;
import com.example.moviecatalogue.viewmodel.FavMovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.moviecatalogue.ui.DetailActivity.KEY_EXTRA_FAV_MOVIE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {

    private Cursor cursor;

    private FavMovieViewModel favMovieViewModel;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mMainActivity.setupNavigationDrawer(toolbar);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mMainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favMovieViewModel = new ViewModelProvider(this).get(FavMovieViewModel.class);
        FloatingActionButton btnDeleteAll = view.findViewById(R.id.btn_delete_all_favorite);
        RecyclerView recyclerView = view.findViewById(R.id.rv_favorite_movie);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setHasFixedSize(true);

            final FavoriteMovieAdapter adapter = new FavoriteMovieAdapter();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);


            favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);
            favMovieViewModel.getAllFavMovie().observe(getViewLifecycleOwner(), new Observer<List<FavoriteMovieModel>>() {
                @Override
                public void onChanged(List<FavoriteMovieModel> favoriteMovieModels) {
                    adapter.setMovieData(favoriteMovieModels);
                }
            });

            adapter.OnItemClickCallback(new FavoriteMovieAdapter.OnItemClickCallback() {
                @Override
                public void onItemClicked(FavoriteMovieModel movieModel) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra(KEY_EXTRA_FAV_MOVIE, movieModel);
                    startActivity(intent);
                }
            });
            //delete all fav
            btnDeleteAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(R.string.delete_favorite)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    favMovieViewModel.deleteAllFavMovie();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.show();
                }
            });
        }

        }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    }


