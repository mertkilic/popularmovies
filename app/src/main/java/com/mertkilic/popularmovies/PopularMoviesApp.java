package com.mertkilic.popularmovies;

import android.app.Application;
import android.content.res.Resources;

import com.mertkilic.popularmovies.di.component.AppComponent;
import com.mertkilic.popularmovies.di.component.DaggerAppComponent;
import com.mertkilic.popularmovies.di.module.AppModule;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
public class PopularMoviesApp extends Application {
    private static PopularMoviesApp instance;
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }
    public static Resources getRes() { return instance.getResources(); }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
