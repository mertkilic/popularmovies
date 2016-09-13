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

    public void addMovies(List<Movie> movies) {
        if (this.movies.isEmpty())
            this.movies = movies;
        else
            this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = -1;

        if (viewType == Movie.TYPE_POPULAR)
            layoutId = R.layout.item_popular_movie;
        else if (viewType == Movie.TYPE_SEARCH)
            layoutId = R.layout.item_search_result;

        return ViewHolder.create(parent, layoutId);
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

        static ViewHolder create(ViewGroup parent, int id) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), id, parent, false);
            return new ViewHolder(binding);
        }

        public void bindTo(Movie movie) {
            binding.setVariable(BR.movie, movie);
            binding.executePendingBindings();
        }
    }
}
