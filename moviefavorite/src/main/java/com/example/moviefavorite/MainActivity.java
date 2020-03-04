package com.example.moviefavorite;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.moviefavorite.DatabaseContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    MovieAdapter movieAdapter;
    private final int LOAD_MOVIE_ID = 110;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView listView = findViewById(R.id.list_view);
        movieAdapter = new MovieAdapter(this, null, true);
        listView.setAdapter(movieAdapter);
        getLoaderManager().initLoader(LOAD_MOVIE_ID, null,  this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(LOAD_MOVIE_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, CONTENT_URI, null,null,null,null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data!=null) {
            movieAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        movieAdapter.swapCursor(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(LOAD_MOVIE_ID);
    }

}
