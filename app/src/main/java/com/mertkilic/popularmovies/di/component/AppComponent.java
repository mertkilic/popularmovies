package com.mertkilic.popularmovies.di.component;

import android.app.Application;
import android.content.res.Resources;

import com.mertkilic.popularmovies.data.api.TrackTvService;
import com.mertkilic.popularmovies.di.module.AppModule;
import com.mertkilic.popularmovies.di.scopes.PerApplication;

import dagger.Component;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@PerApplication
@Component(modules = {AppModule.class})
public interface AppComponent {
    Application application();
    Resources resources();
    TrackTvService service();
}
