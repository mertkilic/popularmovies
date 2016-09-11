package com.mertkilic.popularmovies.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mertkilic.popularmovies.data.model.Movie;
import com.mertkilic.popularmovies.databinding.ItemPopularMovieBinding;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder> {

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
        return ViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemPopularMovieBinding binding;

        public ViewHolder(ItemPopularMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemPopularMovieBinding binding = ItemPopularMovieBinding.inflate(inflater, parent, false);
            return new ViewHolder(binding);
        }

        public void bindTo(Movie movie) {
            binding.setMovie(movie);
        }
    }
}
