package com.example.itiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.itiproject.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieAdapterCallBack {
    ActivityMainBinding binding;
    MyModel myModel;
    List<Movie> list;
    MovieAdapter adapter;
    MovieAdapter adapter2;
    final String BASE_URL="https://api.themoviedb.org/3/";
    final String API_KEY="6557d01ac95a807a036e5e9e325bb3f0";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myModel=new ViewModelProvider(this).get(MyModel.class);
        myModel.startRetroFit();
        adapter=new MovieAdapter(this);
        adapter2=new MovieAdapter(this);
        observe();
        observeTop();
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        Log.d("token", token);
//                        // Log and toast
//                        Toast.makeText(MainActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        binding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });




    }
    public void observe()
    {
        myModel.popularMoviesData.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieList) {
                adapter.setMovieList(movieList);
                binding.listOne.setAdapter(adapter);
                binding.listOne.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));
            }
        });
    }
    public void observeTop()
    {
        myModel.topRatedMoviesData.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieList) {
                adapter2.setMovieList(movieList);
                binding.listTwo.setAdapter(adapter2);
                binding.listTwo.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));
            }
        });
    }


    @Override
    public void getMovie(Movie movie) {
        Intent intent=new Intent(this,MovieDetails.class);
        intent.putExtra("movie",movie);
        startActivity(intent);
    }
}