package com.example.itiproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Movie> movieList;
    MovieAdapterCallBack movieAdapterCallBack;
    public MovieAdapter(MovieAdapterCallBack movieAdapterCallBack)
    {
        this.movieAdapterCallBack=movieAdapterCallBack;
    }
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.movie_card,parent,false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieAdapterViewHolder)holder).bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView title;
        TextView rating;
        public MovieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            poster=itemView.findViewById(R.id.movie_poster);
            title=itemView.findViewById((R.id.movie_title));
            rating=itemView.findViewById(R.id.movie_rating);
        }
        public void bind(Movie movie)
        {
            title.setText(movie.getTitle());
            rating.setText(String.valueOf(movie.getRating()));
            Glide.with(itemView.getContext()).load(movie.getPoster()).into(poster);
            itemView.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieAdapterCallBack.getMovie(movie);
                }
            });
        }
    }
}
