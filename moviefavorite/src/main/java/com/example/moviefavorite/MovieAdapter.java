package com.example.moviefavorite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import static com.example.moviefavorite.DatabaseContract.MovieColumns.POSTER_PATH;
import static com.example.moviefavorite.DatabaseContract.MovieColumns.TITLE;
import static com.example.moviefavorite.DatabaseContract.getString;

public class MovieAdapter extends CursorAdapter {
    public MovieAdapter(Context context, Cursor c, boolean reQuery) {
        super(context, c, reQuery);
    }

    @Override
    public Cursor getCursor() {
        return  super.getCursor();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor!=null) {
            ImageView imgPhoto;
            TextView tvName;
            CardView cardView;

            //bind
            imgPhoto = view.findViewById(R.id.img_movie);
            tvName = view.findViewById(R.id.tv_name);
            cardView = view.findViewById(R.id.cardview_id);

            //set data
            tvName.setText(getString(cursor, TITLE));
            Picasso.with(context)
                    .load(getString(cursor, POSTER_PATH))
                    .into(imgPhoto);
        }

    }
}
