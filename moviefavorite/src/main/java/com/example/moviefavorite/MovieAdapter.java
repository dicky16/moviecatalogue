package com.example.moviefavorite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Cursor mCursor;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(mCursor.moveToPosition(i));
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public void setData(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_fav)
        TextView title;
        @BindView(R.id.description_fav)
        TextView description;
        @BindView(R.id.poster_fav)
        ImageView poster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(boolean moveToPosition) {
            if (moveToPosition) {
                title.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_TITLE)));
                description.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_DESCRIPTION)));
                Picasso.with(context).load(mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_POSTER))).into(poster);
            }
        }
    }
//    public MovieAdapter(Context context, Cursor c, boolean reQuery) {
//        super(context, c, reQuery);
//    }
//
//    @Override
//    public Cursor getCursor() {
//        return  super.getCursor();
//    }
//
//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
//        return view;
//    }
//
//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        if (cursor!=null) {
//            ImageView imgPhoto;
//            TextView tvName;
//            CardView cardView;
//
//            //bind
//            imgPhoto = view.findViewById(R.id.img_movie);
//            tvName = view.findViewById(R.id.tv_name);
//            cardView = view.findViewById(R.id.cardview_id);
//
//            //set data
//            tvName.setText(getString(cursor, TITLE));
//            Picasso.with(context)
//                    .load(getString(cursor, POSTER_PATH))
//                    .into(imgPhoto);
//        }
//
//    }
}
