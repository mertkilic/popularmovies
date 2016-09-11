package com.mertkilic.popularmovies.view.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public class SpaceItemDecorator extends RecyclerView.ItemDecoration {

    private final int space;

    public SpaceItemDecorator(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        outRect.right = space;
        outRect.left = space;
    }
}
