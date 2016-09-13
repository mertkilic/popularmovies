package com.mertkilic.popularmovies.view;

import com.mertkilic.popularmovies.data.model.Movie;

import java.util.List;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public interface PopularMoviesView extends View {
    void onMoviesLoaded(List<Movie> popularMovies);
}
