package com.neha.lenskartimdb.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neha.lenskartimdb.R;
import com.neha.lenskartimdb.databinding.MovieItemListBinding;
import com.neha.lenskartimdb.listeners.CardClickListener;
import com.neha.lenskartimdb.model.Movie;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    private List<Movie> movieList;
    private CardClickListener cardClickListener;

    public RecyclerviewAdapter(List<Movie> movieList, Context context){
        this.movieList = movieList;
        this.cardClickListener = (CardClickListener) context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_item_list, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            final Movie movie = movieList.get(position);
            holder.binding.setMovie(movie);

            holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardClickListener.onCardClick(movie);
                }
            });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MovieItemListBinding binding;
        public MyViewHolder(MovieItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
