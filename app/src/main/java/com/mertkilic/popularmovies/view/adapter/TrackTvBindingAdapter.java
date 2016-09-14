package com.mertkilic.popularmovies.view.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mertkilic.popularmovies.data.model.Poster;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@SuppressWarnings("unused")
public class TrackTvBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void loadImage(ImageView imageView, String imageUrl, Drawable drawable) {
        if (imageUrl == null)
            Glide.with(imageView.getContext()).load("").placeholder(drawable).into(imageView);
        else
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
