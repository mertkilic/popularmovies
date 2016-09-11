package com.mertkilic.popularmovies.di.component;

import com.mertkilic.popularmovies.di.module.AppModule;
import com.mertkilic.popularmovies.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
