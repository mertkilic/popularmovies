package com.mertkilic.popularmovies.view.decorator;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int position = parent.getChildAdapterPosition(view);

            if (position < spanCount)
                outRect.top = space;
            else
                outRect.top = 0;

        } else {
            outRect.top = space;
        }

        outRect.bottom = space;
        outRect.right = space;
        outRect.left = space;
    }
}
