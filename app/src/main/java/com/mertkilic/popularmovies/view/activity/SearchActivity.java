package com.mertkilic.popularmovies.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;

import com.mertkilic.popularmovies.R;
import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.databinding.ActivitySearchBinding;
import com.mertkilic.popularmovies.listener.EndlessRecyclerViewScrollListener;
import com.mertkilic.popularmovies.view.TraktSearchView;
import com.mertkilic.popularmovies.view.adapter.MoviesAdapter;
import com.mertkilic.popularmovies.view.decorator.SpaceItemDecorator;
import com.mertkilic.popularmovies.viewmodel.SearchViewModel;

import java.util.Collections;
import java.util.List;

public class SearchActivity extends ViewModelActivity<SearchViewModel> implements TraktSearchView {

    MoviesAdapter adapter;
    ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityComponent().inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        adapter = new MoviesAdapter();
        binding.searchResults.setAdapter(adapter);
        binding.searchResults.addItemDecoration(new SpaceItemDecorator(getResources().getDimensionPixelSize(R.dimen.space_linear_item)));
        binding.searchResults.addOnScrollListener(new EndlessRecyclerViewScrollListener(binding.searchResults.getLayoutManager()) {
            @Override
            public void onLoadMore(int page) {
                viewModel.onLoadMore(page);
            }
        });
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
        adapter.addSearchResults(Collections.EMPTY_LIST, "");
        binding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSearchFinish(List<Movie> searchResults, String keyword) {
        binding.progress.setVisibility(View.GONE);
        adapter.addSearchResults(searchResults, keyword);
    }
}
