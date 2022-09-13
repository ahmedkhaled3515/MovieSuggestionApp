package com.example.itiproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi2 {
    @GET("movie/top_rated")
    Call<MovieResponse> getMovies(@Query("api_key")String apiKey);
}

