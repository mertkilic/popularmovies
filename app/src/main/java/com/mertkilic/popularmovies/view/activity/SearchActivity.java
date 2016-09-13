package com.mertkilic.popularmovies.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mertkilic.popularmovies.R;
import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.databinding.ActivitySearchBinding;
import com.mertkilic.popularmovies.view.TraktSearchView;
import com.mertkilic.popularmovies.viewmodel.SearchViewModel;

import java.util.List;

public class SearchActivity extends ViewModelActivity<SearchViewModel> implements TraktSearchView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityComponent().inject(this);
        super.onCreate(savedInstanceState);
        ActivitySearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
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
        MenuItemCompat.setOnActionExpandListener(searchItem,viewModel);
        return true;
    }

    @Override
    public void onSearchBackButtonPressed() {
        finish();
    }

    @Override
    public void onResultsRetrieved(List<Movie> searchResults) {
        Log.d("","");
    }
}
