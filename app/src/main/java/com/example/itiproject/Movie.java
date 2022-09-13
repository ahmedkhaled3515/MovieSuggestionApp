package com.example.itiproject;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
     @Json(name="poster_path") String poster;
    private @Json(name ="vote_average") double rating;
    public Movie(String title,String poster,double rating)
    {
        this.title=title;
        this.poster=poster;
        this.rating=rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        String base="https://image.tmdb.org/t/p/w500";
        return base+poster;
    }

    public double getRating() {
        return rating;
    }
}
