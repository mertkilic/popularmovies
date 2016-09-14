package com.mertkilic.popularmovies.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mertkilic.popularmovies.R;
import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.databinding.ActivitySearchBinding;
import com.mertkilic.popularmovies.listener.EndlessRecyclerViewScrollListener;
import com.mertkilic.popularmovies.view.TraktSearchView;
import com.mertkilic.popularmovies.view.adapter.MoviesAdapter;
import com.mertkilic.popularmovies.view.decorator.SpaceItemDecorator;
import com.mertkilic.popularmovies.viewmodel.SearchViewModel;

import java.net.UnknownHostException;
import java.util.List;

public class SearchActivity extends ViewModelActivity<SearchViewModel> implements TraktSearchView {

    MoviesAdapter adapter;
    ActivitySearchBinding binding;
    EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityComponent().inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        adapter = new MoviesAdapter();
        binding.searchResults.setAdapter(adapter);
        binding.searchResults.addItemDecoration(new SpaceItemDecorator(getResources().getDimensionPixelSize(R.dimen.space_linear_item)));
        scrollListener = new EndlessRecyclerViewScrollListener(binding.searchResults.getLayoutManager()) {
            @Override
            public void onLoadMore(int page) {
                viewModel.onLoadMore(page);
            }
        };
        binding.searchResults.addOnScrollListener(scrollListener);
        binding.swipeRefresh.setOnRefreshListener(viewModel);
        binding.searchResults.setOnTouchListener(viewModel);
    }

    @Override
    protected void initViewModel() {
        viewModel.setView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(viewModel);
        searchItem.expandActionView();
        MenuItemCompat.setOnActionExpandListener(searchItem, viewModel);
        return true;
    }

    @Override
    public void onSearchBackButtonPressed() {
        finish();
    }

    @Override
    public void onSearchBegin() {
        hideEmptyLayout();
        toggleProgress(true);
    }

    @Override
    public void onSearchFinish(List<Movie> searchResults, String keyword, boolean loadMore) {
        toggleProgress(false);
        hideEmptyLayout();
        if (searchResults.isEmpty() && !loadMore) {
            showEmptyLayout(getString(R.string.error_no_results, keyword));
        } else
            adapter.addSearchResults(searchResults, keyword);
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
        clearList();
        hideEmptyLayout();
        toggleProgress(false);
    }

    private void clearList() {
        scrollListener.reset();
        adapter.clearMovies();
    }

    private void showEmptyLayout(String error) {
        clearList();
        binding.emptyLayout.tvError.setVisibility(View.VISIBLE);
        binding.emptyLayout.tvError.setText(error);
    }

    private void hideEmptyLayout() {
        binding.emptyLayout.tvError.setVisibility(View.GONE);
    }

    private void toggleProgress(boolean refresh) {
        binding.swipeRefresh.setRefreshing(refresh);
    }
}
