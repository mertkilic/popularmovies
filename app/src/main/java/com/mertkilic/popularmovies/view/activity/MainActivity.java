package com.mertkilic.popularmovies.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.mertkilic.popularmovies.PopularMoviesApp;
import com.mertkilic.popularmovies.R;
import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.databinding.ActivityMainBinding;
import com.mertkilic.popularmovies.listener.EndlessRecyclerViewScrollListener;
import com.mertkilic.popularmovies.view.PopularMoviesView;
import com.mertkilic.popularmovies.view.adapter.PopularMoviesAdapter;
import com.mertkilic.popularmovies.view.decorator.SpaceItemDecorator;
import com.mertkilic.popularmovies.viewmodel.MainViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends ViewModelActivity implements PopularMoviesView {

    @Inject
    MainViewModel mainViewModel;

    PopularMoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PopularMoviesApp.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(mainViewModel);

        adapter = new PopularMoviesAdapter();
        binding.popularMovies.setAdapter(adapter);
        binding.popularMovies.addItemDecoration(new SpaceItemDecorator(getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin)));
        binding.popularMovies.addOnScrollListener(new EndlessRecyclerViewScrollListener(binding.popularMovies.getLayoutManager()) {
            @Override
            public void onLoadMore(int page) {
                mainViewModel.onLoadMore(page);
            }
        });
    }

    @Override
    protected void initViewModel() {
        viewModel = mainViewModel;
        mainViewModel.setView(this);
        mainViewModel.initialize();
    }

    @Override
    public void onMoviesLoaded(List<Movie> popularMovies) {
        adapter.addMovies(popularMovies);
    }
}
