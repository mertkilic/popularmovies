package com.mertkilic.popularmovies.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mertkilic.popularmovies.R;
import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.databinding.ActivityMainBinding;
import com.mertkilic.popularmovies.listener.EndlessRecyclerViewScrollListener;
import com.mertkilic.popularmovies.view.PopularMoviesView;
import com.mertkilic.popularmovies.view.adapter.PopularMoviesAdapter;
import com.mertkilic.popularmovies.view.decorator.SpaceItemDecorator;
import com.mertkilic.popularmovies.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends ViewModelActivity<MainViewModel> implements PopularMoviesView {

    PopularMoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityComponent().inject(this);
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(viewModel);

        adapter = new PopularMoviesAdapter();
        binding.popularMovies.setAdapter(adapter);
        binding.popularMovies.addItemDecoration(new SpaceItemDecorator(getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin)));
        binding.popularMovies.addOnScrollListener(new EndlessRecyclerViewScrollListener(binding.popularMovies.getLayoutManager()) {
            @Override
            public void onLoadMore(int page) {
                viewModel.onLoadMore(page);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItemCompat.setActionView(menu.findItem(R.id.search), null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("search", "clicked");
        startActivity(new Intent(this, SearchActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModel() {
        viewModel.setView(this);
        viewModel.initialize();
    }

    @Override
    public void onMoviesLoaded(List<Movie> popularMovies) {
        adapter.addMovies(popularMovies);
    }
}
