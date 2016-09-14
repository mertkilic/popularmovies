package com.mertkilic.popularmovies.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by Mert Kilic on 12.9.2016.
 * Copied and rearranged from http://stackoverflow.com/a/26561717/3640576
 */
public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    LayoutManager mLayoutManager;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    private int currentPage = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public EndlessRecyclerViewScrollListener(LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        if (layoutManager instanceof GridLayoutManager)
            visibleThreshold = visibleThreshold * ((GridLayoutManager) layoutManager).getSpanCount();
        else if (layoutManager instanceof StaggeredGridLayoutManager)
            visibleThreshold = visibleThreshold * ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        super.onScrolled(view, dx, dy);

        visibleItemCount = view.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();

        if (mLayoutManager instanceof LinearLayoutManager) {
            firstVisibleItem = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        } else if (mLayoutManager instanceof GridLayoutManager) {
            firstVisibleItem = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        }

        if(totalItemCount == 0)
            return;

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            //End has been reached
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public void reset() {
        currentPage = 1;
        previousTotal = 0;
    }

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore(int page);

}
