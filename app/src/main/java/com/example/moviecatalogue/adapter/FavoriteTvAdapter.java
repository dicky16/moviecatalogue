package com.example.moviecatalogue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.FavoriteMovieModel;
import com.example.moviecatalogue.model.FavoriteTvModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteTvAdapter extends RecyclerView.Adapter<FavoriteTvAdapter.TvViewHolder>{

    private List<FavoriteTvModel> tvData = new ArrayList<>();
    private OnItemClickCallbackTv onItemClickCallback;
//    private OnItemClickCallback onItemClickCallbackLong;

    public void OnItemClickCallback(OnItemClickCallbackTv onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

//    public void OnItemClickLong(OnItemClickCallback onItemClickCallbackLong) {
//        this.onItemClickCallbackLong = onItemClickCallbackLong;
//    }

    public void setTvData(List<FavoriteTvModel> movies) {
        tvData.clear();
        tvData.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        TvViewHolder viewHolder = new TvViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewHolder holder, int position) {
        final FavoriteTvModel tvModel = tvData.get(position);
        holder.tvName.setText(tvModel.getTitle());
        Picasso.with(holder.itemView.getContext())
                .load(tvModel.getPoster_path())
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(tvData.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvName;
        CardView cardView;
        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_movie);
            tvName = itemView.findViewById(R.id.tv_name);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }

    public interface OnItemClickCallbackTv {
        void onItemClicked(FavoriteTvModel tvModel);
    }
}
