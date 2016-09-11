package com.mertkilic.popularmovies.view.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mertkilic.popularmovies.data.model.Thumb;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@SuppressWarnings("unused")
public class TrackTvBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView imageView, Thumb image) {
        Glide.with(imageView.getContext()).load(image.getThumbUrl()).into(imageView);
    }

    @BindingAdapter({"imageUrl", "placeholder"})
    public static void loadImage(ImageView imageView, String imageUrl, Drawable drawable) {
        Glide.with(imageView.getContext()).load(imageUrl).placeholder(drawable).into(imageView);
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}
