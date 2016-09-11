package com.mertkilic.popularmovies.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.mertkilic.popularmovies.PopularMoviesApp;
import com.mertkilic.popularmovies.R;
import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.databinding.ActivityMainBinding;
import com.mertkilic.popularmovies.view.PopularMoviesView;
import com.mertkilic.popularmovies.viewmodel.MainViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends ViewModelActivity implements PopularMoviesView{

    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PopularMoviesApp.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setHandler(mainViewModel);
    }

    @Override
    protected void initViewModel() {
        viewModel = mainViewModel;
        mainViewModel.setView(this);
        mainViewModel.initialize();
    }

    @Override
    public void onMoviesLoaded(List<Movie> popularMovies) {
        Log.d("","");
    }
}
