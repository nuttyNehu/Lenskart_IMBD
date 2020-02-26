package com.neha.lenskartimdb.viewmodel;

import android.view.View;

import com.neha.lenskartimdb.model.ImdbMovie;
import com.neha.lenskartimdb.repository.MovieRepository;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImdbMovieViewModel extends ViewModel {

    private MutableLiveData<ImdbMovie> movieMutableLiveData;
    private MovieRepository movieRepository;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    public ObservableInt showRecyclerView;


    public void init() {
        if (movieMutableLiveData != null) {
            return;
        }
        movieRepository = MovieRepository.getInstance();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
        showRecyclerView = new ObservableInt(View.GONE);
        movieMutableLiveData = movieRepository.getMovieList();
    }

    public LiveData<ImdbMovie> getMovieList() {
        return movieMutableLiveData;
    }
}
