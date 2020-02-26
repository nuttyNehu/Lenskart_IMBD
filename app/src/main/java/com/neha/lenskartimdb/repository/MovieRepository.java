package com.neha.lenskartimdb.repository;

import com.neha.lenskartimdb.base.Constants;
import com.neha.lenskartimdb.model.ImdbMovie;
import com.neha.lenskartimdb.retrofit.MovieApi;
import com.neha.lenskartimdb.retrofit.RestService;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository movieRepository;
    private MovieApi movieApi;

    public static MovieRepository getInstance(){
        if(movieRepository==null)
            movieRepository = new MovieRepository();

        return movieRepository;
    }

    private MovieRepository(){
        movieApi = RestService.cteateService(MovieApi.class);
    }

    public MutableLiveData<ImdbMovie> getMovieList(){
        final MutableLiveData<ImdbMovie> movieMutableLiveData = new MutableLiveData<>();

        movieApi.getImdbMovieList(Constants.API_KEY,Constants.QUERY,Constants.PAGE).enqueue(new Callback<ImdbMovie>() {
            @Override
            public void onResponse(Call<ImdbMovie> call, Response<ImdbMovie> response) {
                if(response.isSuccessful() && response.body()!=null){
                    movieMutableLiveData.setValue(response.body());
                }else{
                    movieMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ImdbMovie> call, Throwable t) {
                    movieMutableLiveData.setValue(null);
            }
        });

        return movieMutableLiveData;
    }


}
