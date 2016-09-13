package com.mertkilic.popularmovies.data.api;

import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.data.model.SearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public interface TrackTvService {

    String END_POINT = "https://api.trakt.tv/";

    @GET("movies/popular?extended=images")
    Call<List<Movie>> getPopularMovies(@Query("page") int page);

    @GET("search/movie?extended=full,images")
    Call<List<SearchResult>> search(@Query("page") int page, @Query("query") String keyword);
}
