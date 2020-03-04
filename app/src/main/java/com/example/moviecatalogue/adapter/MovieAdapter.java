package com.example.moviecatalogue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieModel> movieData = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public void OnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }
    public void setMovieData(ArrayList<MovieModel> movies) {
        movieData.clear();
        movieData.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        MovieModel movieModel = movieData.get(position);
        holder.tvName.setText(movieModel.getTitle());
        Picasso.with(holder.itemView.getContext())
                .load(movieModel.getPoster_path())
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                 onItemClickCallback.onItemClicked(movieData.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvName;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_movie);
            tvName = itemView.findViewById(R.id.tv_name);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(MovieModel movieModel);
    }

}
