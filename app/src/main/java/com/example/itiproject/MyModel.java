package com.example.itiproject;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MyModel extends ViewModel {
    final String BASE_URL="https://api.themoviedb.org/3/";
    final String API_KEY="6557d01ac95a807a036e5e9e325bb3f0";
    public MutableLiveData<List<Movie>> popularMoviesData=new MutableLiveData<List<Movie>>();
    public MutableLiveData<List<Movie>> topRatedMoviesData=new MutableLiveData<List<Movie>>();
    public MyModel()
    {
    }
    void startRetroFit()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        MovieApi2 movieApi2=retrofit.create(MovieApi2.class);
        Call<MovieResponse> call = movieApi.getMovies(API_KEY);
        Call<MovieResponse> call2=movieApi2.getMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
                         @Override
                         public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                             MovieResponse movieResponse=response.body();
                             popularMoviesData.postValue(movieResponse.getResults());
                         }

                         @Override
                         public void onFailure(Call<MovieResponse> call, Throwable t) {

                         }
                     }
        );
        call2.enqueue(new Callback<MovieResponse>() {
                         @Override
                         public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                             MovieResponse movieResponse=response.body();
                             topRatedMoviesData.postValue(movieResponse.getResults());
                         }

                         @Override
                         public void onFailure(Call<MovieResponse> call, Throwable t) {

                         }
                     }
        );

    }
}
