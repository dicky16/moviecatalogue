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
import com.example.moviecatalogue.adapter.TvAdapter;
import com.example.moviecatalogue.model.TvModel;
import com.example.moviecatalogue.ui.DetailActivity;
import com.example.moviecatalogue.ui.SearchResultActivity;
import com.example.moviecatalogue.ui.SettingActivity;
import com.example.moviecatalogue.viewmodel.TvViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.moviecatalogue.ui.SearchResultActivity.FROM;
import static com.example.moviecatalogue.ui.SearchResultActivity.SEARCH_QUERY;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    //search view
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

private TvAdapter tvAdapter;
private ProgressBar progressBar;

private Observer<List<TvModel>> getTvShow = new Observer<List<TvModel>>() {
    @Override
    public void onChanged(List<TvModel> tvModels) {
        if (tvModels != null) {
            tvAdapter.setTvData((ArrayList<TvModel>) tvModels);
            showLoading(false);
        }
    }
};

    public TvFragment() {
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
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tv);
        progressBar = view.findViewById(R.id.pb_loading);
        TvViewModel tvViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        tvViewModel.getListTv().observe(getViewLifecycleOwner(), getTvShow);
        tvAdapter = new TvAdapter();
        tvAdapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(tvAdapter);
        tvAdapter.OnItemClickCallback(new TvAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvModel tvModel) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_EXTRA_TV, tvModel);
                Log.d("id", "tv_id : " +tvModel.getId());
                startActivity(intent);
            }
        });

        tvViewModel.setLiveTvData(1, getString(R.string.language));
        showLoading(true);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    //search tv
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
                    String tv = "tv";
                    Intent i = new Intent(getActivity(), SearchResultActivity.class);
                    i.putExtra(SEARCH_QUERY, query);
                    i.putExtra(FROM, tv);
                    getActivity().startActivity(i);
                    return true;
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
