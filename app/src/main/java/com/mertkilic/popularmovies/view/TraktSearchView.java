package com.mertkilic.popularmovies.view;

import com.mertkilic.popularmovies.data.model.Movie;

import java.util.List;

/**
 * Created by Mert Kilic on 12.9.2016.
 */
public interface TraktSearchView extends View {
    void onSearchBackButtonPressed();

    void onResultsRetrieved(List<Movie> searchResults);
}
