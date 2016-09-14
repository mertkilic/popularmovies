package com.mertkilic.popularmovies.viewmodel;


import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
public class SearchViewModel extends ViewModel<TraktSearchView> implements MenuItemCompat.OnActionExpandListener,
        SearchView.OnQueryTextListener, View.OnTouchListener, SwipeRefreshLayout.OnRefreshListener {

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
    public boolean onQueryTextSubmit(String keyword) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String keyword) {
        if (keyword.length() < 3) {
            getView().clearView();
            this.keyword = "";
        } else {
            uiThreadHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getView().onSearchBegin();
                    SearchViewModel.this.keyword = keyword;
                    search(1);
                }
            }, 200);
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //Hide keyboard when touch on recycler view
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return false;
    }


    @Override
    public void onRefresh() {
        if (!TextUtils.isEmpty(keyword)) {
            getView().onSearchBegin();
            search(1);
        } else getView().clearView();
    }


    public void onLoadMore(int page) {
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
                getView().onSearchFinish(movies, keyword);
            }

            @Override
            public void onFailure(Call<List<SearchResult>> call, Throwable t) {
                getView().onError(t);
            }
        });
    }
}
