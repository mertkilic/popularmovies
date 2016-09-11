package com.mertkilic.popularmovies.viewmodel;

import android.os.Handler;
import android.support.annotation.CallSuper;

import com.mertkilic.popularmovies.view.View;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public abstract class ViewModel<V extends View> {

    V view;

    protected Handler uiThreadHandler;

    public abstract void initialize();

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    public void onCreate(Handler handler) {
        uiThreadHandler = handler;
    }

    public void onDestroy() {
        uiThreadHandler = null;
    }

    @CallSuper
    public void onPause() {
    }

    @CallSuper
    public void onResume() {
    }
}
