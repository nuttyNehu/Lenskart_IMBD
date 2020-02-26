package com.neha.lenskartimdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.neha.lenskartimdb.R;
import com.neha.lenskartimdb.base.Constants;
import com.neha.lenskartimdb.databinding.ActivityMainBinding;
import com.neha.lenskartimdb.listeners.CardClickListener;
import com.neha.lenskartimdb.model.ImdbMovie;
import com.neha.lenskartimdb.model.Movie;
import com.neha.lenskartimdb.viewmodel.ImdbMovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements CardClickListener {

    private ActivityMainBinding binding;
    private RecyclerviewAdapter adapter;
    private ImdbMovieViewModel viewModel;
    private List<Movie> movieList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(ImdbMovieViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        binding.setViewmodel(viewModel);
        setViewModelUpdate();
    }

    public void setViewModelUpdate(){
        viewModel.loading.set(View.VISIBLE);
        viewModel.getMovieList().observe(this, new Observer<ImdbMovie>() {
            @Override
            public void onChanged(ImdbMovie imdbMovie) {
                viewModel.loading.set(View.GONE);
                if(imdbMovie!=null && imdbMovie.getResults()!=null && imdbMovie.getResults().size()>0){
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.showRecyclerView.set(View.VISIBLE);
                    List<Movie> products = imdbMovie.getResults();
                    movieList.addAll(products);
                }else{
                    viewModel.showEmpty.set(View.VISIBLE);
                    viewModel.showRecyclerView.set(View.GONE);
                }
                if(adapter!=null)
                    adapter.notifyDataSetChanged();
            }
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new RecyclerviewAdapter(movieList,this);
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
            binding.setMyAdapter(adapter);
            binding.recyclerview.setItemAnimator(new DefaultItemAnimator());
            binding.recyclerview.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCardClick(Movie movie) {

        Intent intent = new Intent(this,MovieDetailsActivity.class);
        intent.putExtra(Constants.MOVIE_INTENT,movie);
        startActivity(intent);
    }
}
