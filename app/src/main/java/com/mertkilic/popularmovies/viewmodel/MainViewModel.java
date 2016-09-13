package com.mertkilic.popularmovies.viewmodel;

import android.util.Log;

import com.mertkilic.popularmovies.data.api.TrackTvService;
import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.di.scopes.PerActivity;
import com.mertkilic.popularmovies.view.PopularMoviesView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@PerActivity
public class MainViewModel extends ViewModel<PopularMoviesView> {

    TrackTvService trackTvService;

    @Inject
    public MainViewModel(TrackTvService trackTvService) {
        this.trackTvService = trackTvService;
    }

    @Override
    public void initialize() {
        getPopularMovies(1);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onLoadMore(int page) {
        getPopularMovies(page);
    }

    private void getPopularMovies(int page) {
        trackTvService.getPopularMovies(page).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                getView().onMoviesLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e("onFailure : ", t.getMessage(), t);
            }
        });
    }
}
