package com.example.itiproject;

import com.squareup.moshi.Json;

import java.util.List;

public class MovieResponse {
     int page;
     List<Movie> results;
//    private @Json(name = "total_pages")int totalPages;
//    private @Json(name = "total_results")int totalResults;
    public MovieResponse(int page,List<Movie> results)
    {
        this.page=page;
        this.results=results;
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

//    public int getTotalPages() {
//        return totalPages;
//    }
//
//    public int getTotalResults() {
//        return totalResults;
//    }
}
