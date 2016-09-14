package com.mertkilic.popularmovies.view.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mertkilic.popularmovies.R;
import com.mertkilic.popularmovies.data.model.Poster;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@SuppressWarnings("unused")
public class TrackTvBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView imageView, String imageUrl) {
        if (imageUrl != null)
            Glide.with(imageView.getContext()).load(imageUrl).crossFade().into(imageView);
    }

    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void loadImage(ImageView imageView, String imageUrl, Drawable drawable) {
        if (imageUrl == null) imageUrl = "";
        Glide.with(imageView.getContext()).load(imageUrl).error(R.drawable.ic_placeholder).crossFade().into(imageView);
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
