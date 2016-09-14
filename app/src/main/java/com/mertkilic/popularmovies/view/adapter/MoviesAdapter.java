package com.mertkilic.popularmovies.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mertkilic.popularmovies.BR;
import com.mertkilic.popularmovies.R;
import com.mertkilic.popularmovies.data.model.Movie;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    List<Movie> movies = Collections.emptyList();
    String keyword = "";

    public void addMovies(List<Movie> movies) {
        if (this.movies.isEmpty())
            this.movies = movies;
        else
            this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void addSearchResults(List<Movie> searchResults, String keyword) {
        if (this.keyword.equals(keyword))
            movies.addAll(searchResults);
        else {
            movies.clear();
            movies = searchResults;
            this.keyword = keyword;
        }
        notifyDataSetChanged();
    }

    public void clearMovies() {
        movies.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = -1;

        if (viewType == Movie.TYPE_POPULAR)
            layoutId = R.layout.item_popular_movie;
        else if (viewType == Movie.TYPE_SEARCH)
            layoutId = R.layout.item_search_result;

        return ViewHolder.create(LayoutInflater.from(parent.getContext()), parent, layoutId);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return movies.get(position).getType();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        static ViewHolder create(LayoutInflater inflater, ViewGroup parent, int layoutId) {
            ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
            return new ViewHolder(binding);
        }

        public void bindTo(Movie movie) {
            if (movie.getType() == Movie.TYPE_POPULAR)
                binding.setVariable(BR.movie, movie);
            else if (movie.getType() == Movie.TYPE_SEARCH)
                binding.setVariable(BR.searchResult, movie);
            binding.executePendingBindings();
        }
    }
}
