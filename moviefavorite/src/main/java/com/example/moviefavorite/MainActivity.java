package com.example.moviefavorite;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int CODE_MOVIE = 1;
    private MovieAdapter adapter;
    @BindView(R.id.list_view)
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        list = findViewById(R.id.list_view);
        list.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        adapter = new MovieAdapter(getApplicationContext());
        list.setAdapter(adapter);
        getLoaderManager().initLoader(CODE_MOVIE, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        switch (i) {
            case CODE_MOVIE:
                return new CursorLoader(getApplicationContext(), DatabaseContract.CONTENT_URI, null, null, null, null);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        if (loader.getId() == CODE_MOVIE) {
            try {
                adapter.setData(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        if (loader.getId() == CODE_MOVIE) {
            adapter.setData(null);
        }
    }
//    MovieAdapter movieAdapter;
//    private final int LOAD_MOVIE_ID = 110;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        GridView listView = findViewById(R.id.list_view);
//        movieAdapter = new MovieAdapter(this, null, true);
//        listView.setAdapter(movieAdapter);
//        getLoaderManager().initLoader(LOAD_MOVIE_ID, null,  this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        getLoaderManager().restartLoader(LOAD_MOVIE_ID, null, this);
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return new CursorLoader(this, CONTENT_URI, null,null,null,null);
//    }
//
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        if (data!=null) {
//            movieAdapter.swapCursor(data);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        movieAdapter.swapCursor(null);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        getSupportLoaderManager().destroyLoader(LOAD_MOVIE_ID);
//    }


}
