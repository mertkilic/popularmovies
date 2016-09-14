package com.mertkilic.popularmovies.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mertkilic.popularmovies.R;
import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.databinding.ActivityMainBinding;
import com.mertkilic.popularmovies.listener.EndlessRecyclerViewScrollListener;
import com.mertkilic.popularmovies.view.PopularMoviesView;
import com.mertkilic.popularmovies.view.adapter.MoviesAdapter;
import com.mertkilic.popularmovies.view.decorator.SpaceItemDecorator;
import com.mertkilic.popularmovies.viewmodel.MainViewModel;

import java.net.UnknownHostException;
import java.util.List;

public class MainActivity extends ViewModelActivity<MainViewModel> implements PopularMoviesView {

    MoviesAdapter adapter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityComponent().inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(viewModel);

        adapter = new MoviesAdapter();
        binding.popularMovies.setAdapter(adapter);
        binding.popularMovies.addItemDecoration(new SpaceItemDecorator(getResources().getDimensionPixelSize(R.dimen.space_grid_item)));
        binding.popularMovies.addOnScrollListener(new EndlessRecyclerViewScrollListener(binding.popularMovies.getLayoutManager()) {
            @Override
            public void onLoadMore(int page) {
                viewModel.onLoadMore(page);
            }
        });
        binding.swipeRefresh.setOnRefreshListener(viewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItemCompat.setActionView(menu.findItem(R.id.search), null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, SearchActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModel() {
        viewModel.setView(this);
        viewModel.initialize();
    }

    @Override
    public void onMoviesLoading() {
        binding.swipeRefresh.setRefreshing(true);
    }

    @Override
    public void onMoviesLoaded(List<Movie> popularMovies) {
        hideEmptyLayout();
        toggleProgress(false);
        adapter.addMovies(popularMovies);
    }

    @Override
    public void onError(Throwable t) {
        toggleProgress(false);
        if (t instanceof UnknownHostException)
            showEmptyLayout(getString(R.string.error_no_connection));
        else
            showEmptyLayout(getString(R.string.error_common));
    }

    @Override
    public void clearView() {
        hideEmptyLayout();
        toggleProgress(false);
    }

    private void showEmptyLayout(String error) {
        binding.popularMovies.setVisibility(View.GONE);
        binding.emptyLayout.tvError.setVisibility(View.VISIBLE);
        binding.emptyLayout.tvError.setText(error);
    }

    private void hideEmptyLayout() {
        binding.emptyLayout.tvError.setVisibility(View.GONE);
        binding.popularMovies.setVisibility(View.VISIBLE);
    }

    private void toggleProgress(boolean refresh) {
        binding.swipeRefresh.setRefreshing(refresh);
    }
}
