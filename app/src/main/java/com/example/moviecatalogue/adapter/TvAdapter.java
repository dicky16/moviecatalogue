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
import com.example.moviecatalogue.model.TvModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {

    private ArrayList<TvModel> tvData = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public void OnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }
    public void setTvData(ArrayList<TvModel> tvs) {
        tvData.clear();
        tvData.addAll(tvs);
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
        TvModel tvModel = tvData.get(position);
        holder.tvName.setText(tvModel.getName());
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
        void onItemClicked(TvModel tvModel);
    }
}
