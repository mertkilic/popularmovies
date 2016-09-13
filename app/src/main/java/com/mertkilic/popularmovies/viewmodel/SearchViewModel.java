package com.mertkilic.popularmovies.viewmodel;


import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mertkilic.popularmovies.data.api.TrackTvService;
import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.data.model.SearchResult;
import com.mertkilic.popularmovies.view.TraktSearchView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mert Kilic on 12.9.2016.
 */
public class SearchViewModel extends ViewModel<TraktSearchView> implements MenuItemCompat.OnActionExpandListener, SearchView.OnQueryTextListener {

    TrackTvService trackTvService;
    String keyword;

    @Inject
    public SearchViewModel(TrackTvService trackTvService) {
        this.trackTvService = trackTvService;
    }

    @Override
    public void initialize() {

    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        getView().onSearchBackButtonPressed();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String keyword) {
        if (keyword.length() >= 3) {
            this.keyword = keyword;
            search(1);
        }
        return false;
    }

    public void onLoadMore(int page){
        search(page);
    }

    private void search(int page) {
        trackTvService.search(page, keyword).enqueue(new Callback<List<SearchResult>>() {
            @Override
            public void onResponse(Call<List<SearchResult>> call, Response<List<SearchResult>> response) {
                List<Movie> movies = new ArrayList<>();
                for (SearchResult searchResult : response.body()) {
                    movies.add(searchResult.getMovie().setType(Movie.TYPE_SEARCH));
                }
                getView().onResultsRetrieved(movies);
            }

            @Override
            public void onFailure(Call<List<SearchResult>> call, Throwable t) {

            }
        });
    }
}
