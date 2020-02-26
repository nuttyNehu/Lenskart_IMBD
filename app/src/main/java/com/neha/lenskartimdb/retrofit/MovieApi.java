package com.neha.lenskartimdb.retrofit;

import com.neha.lenskartimdb.model.ImdbMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie")
    Call<ImdbMovie> getImdbMovieList(@Query("api_key") String apiKey,@Query("query") String query, @Query("page") String page);
}
