package com.kyrsovaya.musiclibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.kyrsovaya.musiclibrary.R;
import com.kyrsovaya.musiclibrary.data.ClickListener;
import com.kyrsovaya.musiclibrary.models.artist.Artist;

import java.util.List;

public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistsAdapter.ViewHolder>{
    List<Artist> topArtistsList;
    Context context;
    public static ClickListener clickListener;

    public TopArtistsAdapter(List<Artist> list, Context context) {
        this.topArtistsList = list;
        this.context = context;
    }

    public void addTopArtistsList(List<Artist> list) {
        this.topArtistsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopArtistsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_top_artists, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopArtistsAdapter.ViewHolder holder, int position) {
        holder.artistNameTextView.setText(topArtistsList.get(position).getName());
        Glide.with(context)
                .load(topArtistsList.get(position).getImage().get(3).getText())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return topArtistsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView artistNameTextView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artistNameTextView = itemView.findViewById(R.id.topArtistNameTextView);
            imageView = itemView.findViewById(R.id.topArtistImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            TopArtistsAdapter.clickListener.onItemClick(getAbsoluteAdapterPosition(), view);
        }

    }

    public void setOnItemClickListener(ClickListener clickListener) {
        TopArtistsAdapter.clickListener = clickListener;
    }
}
