package com.example.moviecatalogue.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.FavoriteTvAdapter;
import com.example.moviecatalogue.model.FavoriteTvModel;
import com.example.moviecatalogue.ui.DetailActivity;
import com.example.moviecatalogue.viewmodel.FavTvViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.moviecatalogue.ui.DetailActivity.KEY_EXTRA_FAV_TV;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment {

    FavTvViewModel favTvViewModel;

    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //init
        FloatingActionButton btnDel = view.findViewById(R.id.btn_delete_all_favorite_tv);
        RecyclerView recyclerView = view.findViewById(R.id.rv_favorite_tv);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setHasFixedSize(true);

            final FavoriteTvAdapter adapter = new FavoriteTvAdapter();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);


            favTvViewModel = ViewModelProviders.of(this).get(FavTvViewModel.class);
            favTvViewModel.getAllFavTv().observe(getViewLifecycleOwner(), new Observer<List<FavoriteTvModel>>() {
                @Override
                public void onChanged(List<FavoriteTvModel> favoriteTvModels) {
                    adapter.setTvData(favoriteTvModels);
                }
            });

            adapter.OnItemClickCallback(new FavoriteTvAdapter.OnItemClickCallbackTv() {
                @Override
                public void onItemClicked(FavoriteTvModel tvModel) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra(KEY_EXTRA_FAV_TV, tvModel);
                    startActivity(intent);
                }
            });

            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(R.string.delete_favorite)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    favTvViewModel.deleteAllFavTv();
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
}
